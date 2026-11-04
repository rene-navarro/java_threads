# Java Threads — Ejemplos de concurrencia en Java

Proyecto didáctico con **22 clases Java** distribuidas en **10 paquetes**. Ilustra la creación de hilos, implementación de `Runnable`, coordinación, interrupción, prioridades, compartición de estado, procesamiento concurrente de archivos y tareas con `ExecutorService`, `Callable` y `FutureTask`.

> **Uso educativo:** algunos programas contienen intencionalmente comportamientos no recomendados en producción (ciclos infinitos, carreras de datos, ausencia de `join`, etc.).

## Requisitos

- JDK compatible con el proyecto (las fuentes se compilaron correctamente con **OpenJDK 21**).
- Gradle Wrapper incluido: **Gradle 9.8.2**, configurado en `gradle/wrapper/gradle-wrapper.properties`.
- Gradle utiliza el plugin `java`; JUnit Jupiter **5.8.1** está declarado para pruebas, pero no se encontraron archivos de pruebas bajo `src/test`.
- No se define clase principal única ni plugin `application`: cada ejemplo se ejecuta por separado.

## Compilación y ejecución

Desde la raíz del repositorio:

```bash
./gradlew classes       # macOS / Linux
# gradlew.bat classes    # Windows
```

Para ejecutar una clase con método `main`:

```bash
java -cp build/classes/java/main mx.unison.e01.SimpleThread
java -cp build/classes/java/main mx.unison.e06.MultiRunner 3 5
```

En Windows, la ruta de clases es la misma; cambie el separador de rutas si agrega varias entradas al classpath. En IntelliJ IDEA puede ejecutar cada clase que contenga `public static void main(String[] args)` desde el icono de ejecución del editor.

> `mx.unison.e04.HiloNumeroLetras` no termina por sí solo y genera salida continuamente: ejecútelo únicamente en un entorno controlado y deténgalo manualmente. `mx.unison.future_task.SequentialExecutionDemo` y `ParallelExecutionDemo` recorren hasta **3,000,000,000** números, por lo que pueden tardar considerablemente.

## Organización

```text
java_threads/
├── build.gradle
├── settings.gradle
├── gradlew / gradlew.bat
├── gradle/wrapper/
└── src/main/java/mx/unison/
    ├── e01/                 SimpleThread.java
    ├── e02/                 HelloThread.java
    ├── e03/                 HelloRunner.java
    ├── e04/                 HiloNumeroLetras.java
    ├── e05/                 MyRunnable.java, MyRunnableMain.java
    ├── e06/                 HelloSlowRunner.java, MultiRunner.java,
    │                       TwoThreadsNoSharing.java, TwoThreadsSharing.java
    ├── prioridades/         NumberPrinter.java, NumberThread.java
    ├── contador/            ContadorLineas.java, LineCounter.java
    ├── primes/              EratosthenesSieve.java, SieveRunner.java
    └── future_task/         CallableCountTask.java, CountService.java,
                            CountTaskData.java, ParallelExecutionDemo.java,
                            RunnableTask.java, SequentialExecutionDemo.java
```

## Documentación de los programas

### 1. Creación básica de hilos (`e01`–`e04`)

| Clase | Función y comportamiento | Ejecución |
|---|---|---|
| `mx.unison.e01.SimpleThread` | Implementa `Runnable`, crea un `Thread` denominado `Hello` y muestra mensajes desde `main` y el hilo secundario. El orden de salida no está garantizado. | `java -cp build/classes/java/main mx.unison.e01.SimpleThread` |
| `mx.unison.e02.HelloThread` | Extiende `Thread`; llama a `start()` y después a `run()` directamente. Esta segunda llamada se ejecuta en el hilo principal, no crea otro hilo. | `java -cp build/classes/java/main mx.unison.e02.HelloThread` |
| `mx.unison.e03.HelloRunner` | Implementa `Runnable`, imprime 500 mensajes desde un hilo y mantiene un contador de instancia. | `java -cp build/classes/java/main mx.unison.e03.HelloRunner` |
| `mx.unison.e04.HiloNumeroLetras` | Lanza dos tareas que imprimen números (1–29) y letras (`a`–`y`) repetidamente en ciclos infinitos. **No termina de forma autónoma.** | `java -cp build/classes/java/main mx.unison.e04.HiloNumeroLetras` |

### 2. Detención cooperativa (`e05`)

- **`MyRunnable`**: implementa una tarea repetitiva que imprime `Running` y duerme tres segundos; consulta un indicador `doStop` mediante métodos sincronizados (`doStop()` y `keepRunning()`).
- **`MyRunnableMain`**: inicia el hilo, espera aproximadamente 15 segundos y solicita la detención. No espera su finalización mediante `join()`; la interrupción durante `sleep()` se registra, pero no se restaura.

```bash
java -cp build/classes/java/main mx.unison.e05.MyRunnableMain
```

### 3. Varios hilos y estado compartido (`e06`)

- **`HelloSlowRunner`**: `Runnable` con contador mutable `i`, límite configurable y pausa de 500 ms; dispone también de `main` para un solo hilo. Imprime valores de `0` al límite inclusive.
- **`MultiRunner`**: recibe límites enteros por argumentos y crea un `Thread` con un `HelloSlowRunner` distinto para cada valor.
- **`TwoThreadsNoSharing`**: ejecuta dos instancias independientes de `HelloSlowRunner`, cada una con su contador.
- **`TwoThreadsSharing`**: ejecuta dos hilos sobre **una misma instancia** de `HelloSlowRunner`; el contador compartido se reinicia y actualiza sin sincronización, por lo que los resultados son no deterministas.

```bash
java -cp build/classes/java/main mx.unison.e06.HelloSlowRunner
java -cp build/classes/java/main mx.unison.e06.MultiRunner 3 7
java -cp build/classes/java/main mx.unison.e06.TwoThreadsNoSharing
java -cp build/classes/java/main mx.unison.e06.TwoThreadsSharing
```

### 4. Prioridades (`prioridades`)

- **`NumberPrinter`**: cinco tareas `Runnable` imprimen 200 veces su número (1–5), con prioridades 1, 10, 3, 8 y 5.
- **`NumberThread`**: versión equivalente basada en clases que extienden `Thread`, con salida sin saltos de línea.

Las prioridades son **sugerencias al planificador**, no garantías de orden ni de reparto de CPU.

```bash
java -cp build/classes/java/main mx.unison.prioridades.NumberPrinter
java -cp build/classes/java/main mx.unison.prioridades.NumberThread
```

### 5. Conteo concurrente de archivos (`contador`)

- **`ContadorLineas`**: extiende `SimpleFileVisitor<Path>` y recorre recursivamente un directorio con `Files.walkFileTree`; crea un hilo por cada archivo cuyo nombre termina en `.txt` (sin distinguir mayúsculas).
- **`LineCounter`**: implementa `Runnable`, abre un archivo de texto, cuenta líneas y suma la longitud de cada línea (sin incluir los caracteres de fin de línea); imprime el nombre del hilo, ruta, conteos y tiempo transcurrido.

```bash
java -cp build/classes/java/main mx.unison.contador.ContadorLineas ./datos
```

`./datos` debe existir. **Precaución:** el programa no limita el número de hilos ni espera explícitamente su finalización; el constructor y el manejo de errores de lectura necesitan correcciones (véase la revisión).

### 6. Búsqueda de primos (`primes`)

- **`EratosthenesSieve`**: `Runnable` que calcula primos **menores que** un límite `max`, probando divisibilidad por primos ya encontrados. Pese al nombre, **no implementa la criba clásica de Eratóstenes**. Para valores pequeños, añade el 2 incondicionalmente aunque el límite sea menor o igual a 2.
- **`SieveRunner`**: interpreta cada argumento como un límite entero y crea un hilo por argumento; imprime las listas producidas por los hilos.

```bash
java -cp build/classes/java/main mx.unison.primes.SieveRunner 20 50 100
```

### 7. Ejecución secuencial y paralela (`future_task`)

| Clase | Responsabilidad |
|---|---|
| `CountService` | Cuenta por iteración los enteros divisibles por un divisor en un intervalo **inclusivo**. |
| `CountTaskData` | Define `MAX_NUMBER = 3_000_000_000L`, `DIVISOR = 3` y dos intervalos contiguos que cubren `0..MAX_NUMBER`. |
| `CallableCountTask` | Implementa `Callable<Long>` y delega el conteo a `CountService`, retornando un resultado. |
| `SequentialExecutionDemo` | Ejecuta el conteo completo en el hilo principal y mide duración con `Instant`/`Duration`. |
| `ParallelExecutionDemo` | Crea un `ExecutorService` fijo de dos hilos y agrega los resultados de dos tareas mediante `FutureTask` o `Future`. |
| `RunnableTask` | Tarea `Runnable` auxiliar que duerme un intervalo configurable y muestra mensajes; no tiene `main` ni es invocada por los demos incluidos. |

```bash
java -cp build/classes/java/main mx.unison.future_task.SequentialExecutionDemo
java -cp build/classes/java/main mx.unison.future_task.ParallelExecutionDemo executeViaFutureTask
java -cp build/classes/java/main mx.unison.future_task.ParallelExecutionDemo submitViaFutureTask
java -cp build/classes/java/main mx.unison.future_task.ParallelExecutionDemo submitViaCallableTask
```

Los tres modos paralelos usan dos tareas y suman sus resultados. El resultado esperado para los datos actuales es **1,000,000,001**, pues se incluye el cero. Los tiempos dependen del equipo y de la carga del sistema. Debe indicarse uno de los tres argumentos al ejecutar `ParallelExecutionDemo`; sin argumentos se produce una excepción.

## Conceptos didácticos

1. `Thread` frente a `Runnable`; diferencia entre `start()` y `run()`.
2. Ciclo de vida del hilo, pausa con `sleep()` y detención cooperativa.
3. Intercalado de salidas, prioridades y planificación no determinista.
4. Estado independiente frente a estado mutable compartido y condiciones de carrera.
5. Paralelización de tareas de archivos y cálculos.
6. `Callable`, `Future`, `FutureTask`, `ExecutorService` y agregación de resultados.
7. Comparación de tiempos de ejecución secuencial y concurrente, considerando los costos de coordinación.


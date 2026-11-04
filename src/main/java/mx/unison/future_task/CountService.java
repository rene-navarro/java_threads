package mx.unison.future_task;

public class CountService {
    /**
     * Count number of divisible.
     *
     * Returns the count of numbers that can be divided by the divisor without
     * remainder.
     *
     * @param firstNumber the start number
     * @param lastNumber  the finish number
     * @param divisor     the divisor
     * @return the count of these numbers which can be divisible by the divisor from
     *         firstNumber to the lastNumber
     */
    public long countNumberOfDivisible(long firstNumber, long lastNumber, long divisor) {
        long count = 0;

        for (long i = firstNumber; i <= lastNumber; i++) {
            if (i % divisor == 0) {
                count++;
            }
        }

        return count;
    }
}

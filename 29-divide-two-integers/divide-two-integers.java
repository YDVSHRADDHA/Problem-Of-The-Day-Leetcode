class Solution {
    public int divide(int dividend, int divisor) {
        // Edge case to prevent overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // Convert to long to prevent overflow, work with absolute values
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;

        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        // If dividend and divisor have different signs, result should be negative
        return ((dividend > 0) ^ (divisor > 0)) ? -result : result;
    }
}

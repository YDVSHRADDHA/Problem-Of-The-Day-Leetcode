class Solution {
    public double myPow(double x, int n) {
        long N = n; // handle edge case when n == Integer.MIN_VALUE
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;
        while (N > 0) {
            if (N % 2 == 1) {
                result *= x;
            }
            x *= x;        // square the base
            N /= 2;        // divide the exponent
        }

        return result;
    }
}

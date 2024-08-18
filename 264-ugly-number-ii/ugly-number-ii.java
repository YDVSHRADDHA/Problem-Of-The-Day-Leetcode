class Solution {
    public int nthUglyNumber(int n) {
       int[] ugly = new int[n];  // Array to store ugly numbers
        ugly[0] = 1;  // The first ugly number is 1
        
        int i2 = 0, i3 = 0, i5 = 0;  // Pointers for 2, 3, and 5
        int nextMultipleOf2 = 2;
        int nextMultipleOf3 = 3;
        int nextMultipleOf5 = 5;
        
        for (int i = 1; i < n; i++) {
            // Choose the smallest next number from the available multiples
            int nextUgly = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            ugly[i] = nextUgly;
            
            // Increment the pointer for the factor used
            if (nextUgly == nextMultipleOf2) {
                i2++;
                nextMultipleOf2 = ugly[i2] * 2;
            }
            if (nextUgly == nextMultipleOf3) {
                i3++;
                nextMultipleOf3 = ugly[i3] * 3;
            }
            if (nextUgly == nextMultipleOf5) {
                i5++;
                nextMultipleOf5 = ugly[i5] * 5;
            }
        }
        
        return ugly[n - 1];  // The nth ugly number
    }

}
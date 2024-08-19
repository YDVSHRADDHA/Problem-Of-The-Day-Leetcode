class Solution {

    public int minSteps(int n) {
      // We start with zero steps, because we haven't done anything yet.
        int ans = 0;
        
        // We start checking from the smallest number, which is 2.
        int d = 2;
        
        // Keep going until we break down n completely.
        while (n > 1) {
            // Check if the number d can divide n evenly (without leaving a remainder).
            while (n % d == 0) {
                // Every time we can divide n by d, it means we need to do d steps.
                // This is like saying we can copy all and paste d times.
                ans += d;
                
                // After using d, reduce n by dividing it by d.
                n /= d;
            }
            // Move to the next number to check if it can divide n.
            d++;
        }
        
        // Return the total number of steps needed.
        return ans;
    }
}
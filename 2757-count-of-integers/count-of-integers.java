class Solution {
    long mod = (long) 1e9 + 7;

    // Main function to count valid numbers between num1 and num2 with digit sum constraints
    public int count(String num1, String num2, int min_sum, int max_sum) {
        StringBuilder sb1 = new StringBuilder(num1);
        int diff = num2.length() - num1.length();
        if (diff > 0) {
            sb1 = sb1.reverse();
            while (diff-- > 0) {
                sb1.append("0");
            }
            sb1 = sb1.reverse();
        }
        num1 = sb1.toString();  // Now num1 has the same length as num2
        Integer[][][][] dp = new Integer[num2.length()][2][2][400];  // DP table
        return helper(num1, num2, min_sum, max_sum, 0, 1, 1, 0, dp);  // Call helper function
    }

    // Recursive DP function to calculate the number of valid numbers in the range
    private int helper(String num1, String num2, int min_sum, int max_sum, int idx, int lowBound, int highBound, int sum, Integer[][][][] dp) {
        // If we've reached the end of the string, check if the sum is within the bounds
        if (idx == num1.length()) return sum >= min_sum ? 1 : 0;
        
        // If we've already calculated this state, return the stored result
        if (dp[idx][lowBound][highBound][sum] != null) return dp[idx][lowBound][highBound][sum];
        
        int max = 9;  // The maximum possible digit at this position
        int min = 0;  // The minimum possible digit at this position
        
        // If we're still bound by num2, limit the max digit to the corresponding digit of num2
        if (highBound == 1) {
            max = num2.charAt(idx) - '0';
        }
        
        // If we're still bound by num1, limit the min digit to the corresponding digit of num1
        if (lowBound == 1) {
            min = num1.charAt(idx) - '0';
        }
        
        long res = 0;
        
        // Try all digits from min to max
        for (int i = min; i <= max; i++) {
            if (sum + i <= max_sum) {
                // Recurse based on whether we're still bound by num1 or num2
                if (i == min && lowBound == 1 && i == max && highBound == 1) {
                    res += helper(num1, num2, min_sum, max_sum, idx + 1, 1, 1, sum + i, dp);
                } else if (i == min && lowBound == 1) {
                    res += helper(num1, num2, min_sum, max_sum, idx + 1, 1, 0, sum + i, dp);
                } else if (i == max && highBound == 1) {
                    res += helper(num1, num2, min_sum, max_sum, idx + 1, 0, 1, sum + i, dp);
                } else {
                    res += helper(num1, num2, min_sum, max_sum, idx + 1, 0, 0, sum + i, dp);
                }
            }
        }
        
        // Store the result in the DP table and return it
        return dp[idx][lowBound][highBound][sum] = (int) (res % mod);
    }
}

class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int MOD = 1_000_000_007;
        
        // To track subsequences ending with '1' and '0'
        long endsWithOne = 0;
        long endsWithZero = 0;
        
        // To track if '0' exists in the string
        boolean hasZero = false;

        // Loop through each character in the string
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                // Every subsequence that ends with '0' or '1' can be extended with '1'
                // Plus the subsequence formed just by the current '1'
                endsWithOne = (endsWithOne + endsWithZero + 1) % MOD;
            } else {
                // A subsequence that ends with '0' can be extended from previous subsequences ending with '1'
                endsWithZero = (endsWithOne + endsWithZero) % MOD;
                hasZero = true;
            }
        }
        
        // The total count is the sum of all subsequences that end with '1' or '0',
        // plus the unique subsequence "0" if '0' exists
        long result = (endsWithOne + endsWithZero + (hasZero ? 1 : 0)) % MOD;

        return (int) result;
    }
}

class Solution {
     static final int MOD = 1_000_000_007;
    public int lengthAfterTransformations(String s, int t) {
        int[] count = new int[26];

        // Initialize count array with frequency of each character in s
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (int i = 0; i < t; i++) {
            int[] nextCount = new int[26];
            for (int j = 0; j < 25; j++) {
                nextCount[j + 1] = (nextCount[j + 1] + count[j]) % MOD;
            }
            // Handle 'z' → 'a' + 'b'
            nextCount[0] = (nextCount[0] + count[25]) % MOD;
            nextCount[1] = (nextCount[1] + count[25]) % MOD;

            count = nextCount; // Move to the next transformation
        }

        // Sum all counts to get total characters
        long total = 0;
        for (int val : count) {
            total = (total + val) % MOD;
        }

        return (int) total;
    }
}

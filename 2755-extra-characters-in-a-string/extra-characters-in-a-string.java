class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(dictionary));
        int n = s.length();
        int[] dp = new int[n + 1];

        // dp[i] means min extra chars from index i to end
        Arrays.fill(dp, -1);

        return helper(0, s, wordSet, dp);
    }

    private int helper(int i, String s, Set<String> wordSet, int[] dp) {
        if (i == s.length()) return 0;
        if (dp[i] != -1) return dp[i];

        int minExtra = Integer.MAX_VALUE;
        StringBuilder curr = new StringBuilder();

        for (int j = i; j < s.length(); j++) {
            curr.append(s.charAt(j));
            String word = curr.toString();

            int extra = wordSet.contains(word) ? 0 : word.length();
            minExtra = Math.min(minExtra, extra + helper(j + 1, s, wordSet, dp));
        }

        return dp[i] = minExtra;
    }
}

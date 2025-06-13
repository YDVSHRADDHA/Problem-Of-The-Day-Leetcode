class Solution {
    public int minimumDeletions(String s) {
        int dp = 0, countB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                countB++;
            } else { // c == 'a'
                dp = Math.min(dp + 1, countB);
            }
        }
        return dp;
    }
}

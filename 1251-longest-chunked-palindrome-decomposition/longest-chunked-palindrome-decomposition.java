class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        if (n == 0) return 0;
        
        for (int length = 1; length <= n / 2; length++) {
            String prefix = text.substring(0, length);
            String suffix = text.substring(n - length);
            if (prefix.equals(suffix)) {
                // Matched chunk found, count 2 and recurse for middle
                return 2 + longestDecomposition(text.substring(length, n - length));
            }
        }
        // No matching prefix-suffix chunk, count whole string as one chunk
        return 1;
    }
}

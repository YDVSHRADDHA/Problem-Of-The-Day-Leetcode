class Solution {
    public String customSortString(String order, String s) {
        int[] count = new int[26];
        
        // Count frequency of each character in s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder result = new StringBuilder();

        // Add characters in the order defined by 'order'
        for (char c : order.toCharArray()) {
            while (count[c - 'a']-- > 0) {
                result.append(c);
            }
        }

        // Add remaining characters not in 'order'
        for (char c = 'a'; c <= 'z'; c++) {
            while (count[c - 'a']-- > 0) {
                result.append(c);
            }
        }

        return result.toString();
    }
}

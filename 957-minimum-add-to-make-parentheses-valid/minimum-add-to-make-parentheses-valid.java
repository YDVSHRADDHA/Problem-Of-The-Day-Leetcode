class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0, insertions = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;  // expecting a future ')'
            } else {
                if (open > 0) {
                    open--;  // match found
                } else {
                    insertions++;  // need an opening '('
                }
            }
        }

        // Add any remaining unmatched '('
        return insertions + open;
    }
}

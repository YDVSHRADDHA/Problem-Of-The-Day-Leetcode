public class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int open = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (open > 0) result.append(ch); // Not the outermost
                open++;
            } else { // ch == ')'
                open--;
                if (open > 0) result.append(ch); // Not the outermost
            }
        }

        return result.toString();
    }
}

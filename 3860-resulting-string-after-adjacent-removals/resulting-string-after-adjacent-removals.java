public class Solution {
    public String resultingString(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int n = stack.length();
            if (n > 0) {
                char top = stack.charAt(n - 1);
                if (isConsecutive(top, ch)) {
                    stack.deleteCharAt(n - 1); // Remove top
                    continue;
                }
            }
            stack.append(ch);
        }

        return stack.toString();
    }

    // Check if two characters are consecutive in the alphabet (circular)
    private boolean isConsecutive(char a, char b) {
        int diff = Math.abs(a - b);
        return diff == 1 || diff == 25; // Includes circular case (a, z)
    }
}

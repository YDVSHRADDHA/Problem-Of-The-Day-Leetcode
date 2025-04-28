class Solution {
    public boolean isNumber(String s) {
        s = s.trim(); // Remove leading and trailing spaces
        
        // Edge case: If the string is empty after trimming, it's not a valid number
        if (s.isEmpty()) {
            return false;
        }

        boolean seenDigit = false;  // Flag to track if we've seen a digit
        boolean seenDot = false;    // Flag to track if we've seen a dot '.'
        boolean seenE = false;      // Flag to track if we've seen 'e' or 'E'
        boolean seenSign = false;   // Flag to track if we've seen a sign ('+' or '-')

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '+' || c == '-') {
                // A sign can appear at the beginning or after 'e' or 'E'
                if (i > 0 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')) {
                    return false;  // Invalid if the sign is not at the beginning or after 'e'/'E'
                }
                // If we have already encountered a digit or 'e', we can't have another sign
            } else if (c == '.') {
                // A dot can only appear once and must not be after 'e' or 'E'
                if (seenDot || seenE) {
                    return false;
                }
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                // 'e' or 'E' can only appear once, and must be after a number
                if (seenE || !seenDigit) {
                    return false;
                }
                seenE = true;
                seenDigit = false;  // Reset seenDigit because the part after 'e' must have digits
            } else if (Character.isDigit(c)) {
                seenDigit = true;  // A digit is valid anywhere
            } else {
                return false;  // Invalid character
            }
        }

        // The string is valid if we've seen at least one digit
        return seenDigit;
    }
}

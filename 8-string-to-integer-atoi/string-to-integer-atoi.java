class Solution {
    public int myAtoi(String s) {
        
             if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        int sign = 1;
        long result = 0; // Use long to prevent overflow during calculation

        // Step 1: Ignore leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check for sign
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: Read digits and convert to integer
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            
            // Step 4: Handle overflow
            if (result * sign < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            if (result * sign > Integer.MAX_VALUE) return Integer.MAX_VALUE;

            i++;
        }

        return (int) (result * sign);
    }

}
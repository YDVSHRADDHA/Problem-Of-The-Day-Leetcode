class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] lastIndex = new int[10];

        // Record the last position of each digit
        for (int i = 0; i < digits.length; i++) {
            lastIndex[digits[i] - '0'] = i;
        }

        // Try to swap the current digit with the largest possible digit on its right
        for (int i = 0; i < digits.length; i++) {
            for (int d = 9; d > digits[i] - '0'; d--) {
                if (lastIndex[d] > i) {
                    // Swap
                    char temp = digits[i];
                    digits[i] = digits[lastIndex[d]];
                    digits[lastIndex[d]] = temp;
                    // Return the result immediately after one swap
                    return Integer.parseInt(new String(digits));
                }
            }
        }

        return num;  // No swap needed
    }
}

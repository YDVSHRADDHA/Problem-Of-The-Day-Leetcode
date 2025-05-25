public class Solution {
    public int primePalindrome(int n) {
        if (n <= 2) return 2;
        if (n <= 3) return 3;
        if (n <= 5) return 5;
        if (n <= 7) return 7;
        if (n <= 11) return 11;

        for (int len = 1; len < 6; len++) { // Only up to 10^8 needed
            int start = (int)Math.pow(10, len - 1);
            int end = (int)Math.pow(10, len);
            for (int root = start; root < end; root++) {
                String left = Integer.toString(root);
                String right = new StringBuilder(left.substring(0, left.length() - 1)).reverse().toString();
                int pal = Integer.parseInt(left + right); // Odd-length palindrome
                if (pal >= n && isPrime(pal)) {
                    return pal;
                }
            }
        }

        return -1; // Should never reach here
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}

public class Solution {
    public int superpalindromesInRange(String left, String right) {
        long L = Long.parseLong(left);
        long R = Long.parseLong(right);
        int count = 0;

        // generate palindromes up to 10^9
        // 1. even-length palindromes
        for (int k = 1; k < 100000; ++k) {
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            String rev = sb.reverse().toString();
            String s = Integer.toString(k) + rev; // even-length
            long num = Long.parseLong(s);
            long square = num * num;
            if (square > R) break;
            if (square >= L && isPalindrome(Long.toString(square))) {
                count++;
            }
        }

        // 2. odd-length palindromes
        for (int k = 1; k < 100000; ++k) {
            String str = Integer.toString(k);
            String rev = new StringBuilder(str.substring(0, str.length() - 1)).reverse().toString();
            String s = str + rev;
            long num = Long.parseLong(s);
            long square = num * num;
            if (square > R) break;
            if (square >= L && isPalindrome(Long.toString(square))) {
                count++;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}

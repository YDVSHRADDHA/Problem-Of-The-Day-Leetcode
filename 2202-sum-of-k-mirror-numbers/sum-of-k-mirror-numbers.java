import java.util.*;

public class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;
        int length = 1;
        
        while (count < n) {
            // Generate all palindromes of length = length
            List<Long> palindromes = generatePalindromes(length);
            for (long palindrome : palindromes) {
                if (isKPalindrome(palindrome, k)) {
                    sum += palindrome;
                    count++;
                    if (count == n) return sum;
                }
            }
            length++;
        }
        
        return sum;
    }

    // Generate palindromes of given length in base 10
    private List<Long> generatePalindromes(int length) {
        List<Long> res = new ArrayList<>();
        if (length == 1) {
            for (int i = 1; i <= 9; i++) {
                res.add((long) i);
            }
            return res;
        }
        
        int halfLen = (length + 1) / 2;
        long start = (long) Math.pow(10, halfLen - 1);
        long end = (long) Math.pow(10, halfLen);
        
        for (long half = start; half < end; half++) {
            String halfStr = Long.toString(half);
            String palindromeStr;
            if (length % 2 == 0) {
                // even length: mirror full half
                palindromeStr = halfStr + new StringBuilder(halfStr).reverse().toString();
            } else {
                // odd length: mirror without last char
                palindromeStr = halfStr + new StringBuilder(halfStr.substring(0, halfStr.length() - 1)).reverse().toString();
            }
            res.add(Long.parseLong(palindromeStr));
        }
        
        return res;
    }

    // Check if a number is palindrome in base-k
    private boolean isKPalindrome(long num, int k) {
        List<Integer> digits = new ArrayList<>();
        long n = num;
        while (n > 0) {
            digits.add((int)(n % k));
            n /= k;
        }
        
        // Check palindrome
        int i = 0, j = digits.size() - 1;
        while (i < j) {
            if (!digits.get(i).equals(digits.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

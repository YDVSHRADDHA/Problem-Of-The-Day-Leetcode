import java.util.*;

import java.math.BigInteger;

public class Solution {
    private final int[] factorial = new int[11]; // to store 0! to 10!

    public Solution() {
        // Precompute factorials
        factorial[0] = 1;
        for (int i = 1; i <= 10; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    public int countGoodIntegers(int n, int k) {
        // Set to hold sorted digit signature of valid k-palindromes
        Set<String> kPalindromes = new HashSet<>();

        // Step 1: Generate all palindromes of length n (as strings to avoid overflow)
        List<String> palindromes = generatePalindromes(n);

        // Step 2: Filter palindromes divisible by k, and store their digit signature
        for (String numStr : palindromes) {
            if (new BigInteger(numStr).mod(BigInteger.valueOf(k)).equals(BigInteger.ZERO)) {
                char[] digits = numStr.toCharArray();
                Arrays.sort(digits); // sort to create a unique signature
                kPalindromes.add(new String(digits));
            }
        }

        // Step 3: For each unique signature, calculate number of permutations without leading zeros
        int total = 0;
        Map<String, Integer> memo = new HashMap<>();

        for (String sig : kPalindromes) {
            if (memo.containsKey(sig)) {
                total += memo.get(sig);
            } else {
                int count = countPermutationsOptimized(sig);
                memo.put(sig, count);
                total += count;
            }
        }

        return total;
    }

    // Generate all palindromic numbers of length n as Strings (no parsing to int)
    private List<String> generatePalindromes(int n) {
        List<String> result = new ArrayList<>();
        int halfLen = (n + 1) / 2;
        int start = (int) Math.pow(10, halfLen - 1);
        int end = (int) Math.pow(10, halfLen);

        for (int i = start; i < end; i++) {
            String firstHalf = Integer.toString(i);
            // Mirror it to get full palindrome
            String secondHalf = new StringBuilder(
                firstHalf.substring(0, n % 2 == 0 ? firstHalf.length() : firstHalf.length() - 1)
            ).reverse().toString();
            String full = firstHalf + secondHalf;
            result.add(full);
        }

        return result;
    }

    // Count all unique permutations of the given digit signature (avoiding leading 0)
    private int countPermutationsOptimized(String sig) {
        int[] freq = new int[10];
        for (char ch : sig.toCharArray()) {
            freq[ch - '0']++;
        }

        // Total permutations = n! / (f0! * f1! * ... * f9!)
        int totalPerm = factorial[sig.length()];
        for (int count : freq) {
            totalPerm /= factorial[count];
        }

        // If no leading zero, return directly
        if (freq[0] == 0) return totalPerm;

        // Now subtract permutations that start with 0
        freq[0]--; // fix 0 as the first digit
        int invalid = factorial[sig.length() - 1];
        for (int count : freq) {
            invalid /= factorial[count];
        }

        return totalPerm - invalid;
    }
}

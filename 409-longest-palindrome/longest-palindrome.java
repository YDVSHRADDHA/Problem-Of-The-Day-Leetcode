import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestPalindrome(String s) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        int length = 0;
        boolean oddCountFound = false;
        
        // Step 2: Calculate the length of the longest palindrome
        for (int count : charCount.values()) {
            if (count % 2 == 0) {
                length += count; // Even counts can be fully used
            } else {
                length += count - 1; // Use the maximum even part of the odd count
                oddCountFound = true; // Remember that we have found at least one odd count
            }
        }
        
        // Step 3: If there is any odd count, we can add one character in the center
        if (oddCountFound) {
            length += 1;
        }
        
        return length;
    }
}

import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] lastIndex = new int[26]; // Stores the last occurrence of each character
        
        // Step 1: Store last index of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Step 2: Iterate and create partitions
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']); // Extend partition boundary
            if (i == end) { // If reached the partition end
                result.add(end - start + 1); // Add partition length
                start = i + 1; // Move to next partition
            }
        }
        
        return result;
    }
}

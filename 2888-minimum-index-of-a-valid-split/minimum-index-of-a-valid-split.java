import java.util.*;

class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Frequency count
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Find the dominant element
        int dominant = -1, totalCount = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() * 2 > n) { // Dominant condition check
                dominant = entry.getKey();
                totalCount = entry.getValue();
                break;
            }
        }

        // If no dominant element, return -1
        if (dominant == -1) return -1;

        // Step 3: Find Minimum Split Index
        int leftCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) == dominant) leftCount++;

            int rightCount = totalCount - leftCount;
            if (leftCount * 2 > (i + 1) && rightCount * 2 > (n - (i + 1))) {
                return i;
            }
        }

        return -1; // No valid split found
    }
}

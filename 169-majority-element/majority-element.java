import java.util.Arrays;

class Solution {
    public int majorityElement(int[] nums) {

//   Approach 1: Using HashMap ---------------------------------------------------------------------------------------------------------------------------------------
HashMap<Integer, Integer> countMap = new HashMap<>();
        int n = nums.length;
        int n1 = n / 2;

        // Count occurrences of each element
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Find the majority element
        for (int key : countMap.keySet()) {
            if (countMap.get(key) > n1) {
                return key;
            }
        }

        // Since the problem guarantees a majority element, we should never reach here
        return -1;
  
    }
}

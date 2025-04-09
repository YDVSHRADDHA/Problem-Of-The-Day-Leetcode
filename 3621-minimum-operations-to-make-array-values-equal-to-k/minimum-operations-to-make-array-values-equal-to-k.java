import java.util.*;

class Solution {
    public int minOperations(int[] nums, int k) {
        
        // Step 1: Check if it's impossible to make all elements equal to k
        // If any element is less than k, we can't increase it → So return -1
        for (int num : nums) {
            if (num < k) {
                return -1;
            }
        }

        // Step 2: Store all unique elements greater than k
        Set<Integer> greater = new HashSet<>();
        for (int num : nums) {
            if (num > k) {
                greater.add(num);  // Only elements strictly greater than k matter
            }
        }

        // Step 3: If no element is greater than k
        // Means all elements are already k → No operation needed
        if (greater.isEmpty()) {
            return 0;
        }

        // Step 4: Convert set to list to sort elements
        List<Integer> list = new ArrayList<>(greater);

        // Sorting elements in descending order to perform operations from largest to smallest
        Collections.sort(list, Collections.reverseOrder());

        // Step 5: The number of operations required is equal to 
        // the number of unique elements greater than k
        return list.size();
    }
}

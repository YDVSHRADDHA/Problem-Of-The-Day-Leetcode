import java.util.Arrays;

class Solution {
    public int majorityElement(int[] nums) {

//   Approach 1: Using HashMap; Time Complexity of O(n) and Space Complexity of O(n) ------------------------------------------------------------------------------------------------------------------------ 
// HashMap<Integer, Integer> countMap = new HashMap<>();
//         int n = nums.length;
//         int n1 = n / 2;

//         // Count occurrences of each element
//         for (int num : nums) {
//             countMap.put(num, countMap.getOrDefault(num, 0) + 1);
//         }

//         // Find the majority element
//         for (int key : countMap.keySet()) {
//             if (countMap.get(key) > n1) {
//                 return key;
//             }
//         }

//         // Since the problem guarantees a majority element, we should never reach here
//         return -1;
        
              
    
// Approach 2: Using Sorting and Counting; Time Complexity: O(n log n) & Space Complexity: O(1)------------------------------------------------------------------------------------------------------------------------      
        int n = nums.length;
        int n1 = n / 2;
        Arrays.sort(nums);

        int currentCount = 1;
        int majorityElement = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                currentCount++;
            } else {
                currentCount = 1;
            }

            if (currentCount > n1) {
                majorityElement = nums[i];
                break;
            }
        }

        return majorityElement;

        //  Could you solve the problem in linear time and in O(1) space? 

    }
}

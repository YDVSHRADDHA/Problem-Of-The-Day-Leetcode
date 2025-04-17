// class Solution {
//     public int numIdenticalPairs(int[] nums) {
//         int count = 0;

//         for (int i = 0; i < nums.length; i++) {
//             for (int j = i+1; j < nums.length; j++) {
//                 if (nums[i] == nums[j]) count++;
//             }
//         }

//         return count;
//     }
// }

import java.util.*;

class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            // If num is already seen, it can form a pair with all previous same nums
            count += freq.getOrDefault(num, 0);
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        return count;
    }
}

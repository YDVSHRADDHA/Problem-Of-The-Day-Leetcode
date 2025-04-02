class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
    
// // \U0001f539 Brute Force:
 
//   int count = 0;
// for (int i = 0; i < nums.length; i++) {
//     for (int j = i+1; j < nums.length; j++) {
//         for (int k = j+1; k < nums.length; k++) {
//             if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
//                 count++;
//             }
//         }
//     }
// }
// return count;
  Set<Integer> set = new HashSet<>();
int count = 0;
for (int num : nums) {
    if (set.contains(num - diff) && set.contains(num - 2 * diff)) {
        count++;
    }
    set.add(num);
}
return count;

    }
}
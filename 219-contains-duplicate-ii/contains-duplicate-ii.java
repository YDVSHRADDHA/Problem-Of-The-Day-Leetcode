class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = i + 1; j <= i + k && j < nums.length; j++) {
        //         if (nums[i] == nums[j]) {
        //             return true;
        //         }
        //     }
        // }
        // return false;

      
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(nums[i])) {
                int previousIndex = numMap.get(nums[i]);
                if (i - previousIndex <= k) {
                    return true;
                }
            }
            numMap.put(nums[i], i);
        }
        return false;
    }
}
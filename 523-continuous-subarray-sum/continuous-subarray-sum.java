class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
    // HashMap to store the first occurrence of each mod value
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1); // base case: mod 0 at index -1 (needed for subarrays starting at index 0)
    
    int prefix = 0;
    
    for (int i = 0; i < nums.length; i++) {
        prefix += nums[i];  // accumulate prefix sum
        
        // Take mod of the prefix sum, handle negative values by adjusting with k
        int mod = (prefix % k + k) % k;

        // If we've seen this mod before and the subarray length is at least 2
        if (map.containsKey(mod)) {
            // Ensure length of the subarray is at least 2
            if (i - map.get(mod) > 1) {
                return true;
            }
        } else {
            // Save the first occurrence of this mod
            map.put(mod, i);
        }
    }
    
    return false;  // No valid subarray found
}

}
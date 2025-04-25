class Solution {
   public int minSubarray(int[] nums, int p) {
    int n = nums.length;

    // Step 1: Calculate total sum modulo p
    int totalMod = 0;
    for (int num : nums) {
        totalMod = (totalMod + num) % p;
    }

    // If the total is already divisible by p, no need to remove anything
    if (totalMod == 0) return 0;

    // Step 2: Use prefix sums and a HashMap to track prefixSum % p
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);  // Base case: prefix sum = 0 at index -1

    int prefix = 0;
    int res = n;  // Initialize result to max (entire array)

    for (int i = 0; i < n; i++) {
        // Update the prefix sum
        prefix = (prefix + nums[i]) % p;

        // Calculate what we need to remove: target = (prefix - totalMod + p) % p
        int target = (prefix - totalMod + p) % p;

        // If we've seen this target prefix before, we found a valid subarray to remove
        if (map.containsKey(target)) {
            res = Math.min(res, i - map.get(target));  // update minimum length
        }

        // Save current prefix % p with its index
        map.put(prefix, i);
    }

    // If we couldn't find such a subarray, return -1
    return res == n ? -1 : res;
}

}
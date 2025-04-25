class Solution {
    public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // One subarray (empty) with sum 0

    int prefix = 0, count = 0;

    for (int num : nums) {
        prefix += num;

        // Handle negative mods by adjusting with k
        int mod = (prefix % k + k) % k;

        // If we've seen this mod before, those previous subarrays make current one divisible
        count += map.getOrDefault(mod, 0);

        // Record the frequency of this mod
        map.put(mod, map.getOrDefault(mod, 0) + 1);
    }

    return count;
}

}
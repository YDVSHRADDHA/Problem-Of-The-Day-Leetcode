class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        // Step 1: sort and remove duplicates
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        
        int[] arr = new int[set.size()];
        int idx = 0;
        for (int num : set) arr[idx++] = num;
        
        Arrays.sort(arr);

        // Step 2: sliding window
        int maxCount = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            while (j < arr.length && arr[j] - arr[i] <= n - 1) {
                j++;
            }
            maxCount = Math.max(maxCount, j - i);
        }
        
        return n - maxCount;
    }
}

class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int low = 0, high = nums[nums.length - 1] - nums[0];

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canFormPairs(nums, p, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canFormPairs(int[] nums, int p, int maxDiff) {
        int count = 0;
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i + 1] - nums[i] <= maxDiff) {
                count++;
                i += 2; // use both indices
            } else {
                i += 1;
            }
        }
        return count >= p;
    }
}

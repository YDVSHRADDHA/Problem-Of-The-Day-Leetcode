class Solution {
    public long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        long[] count = new long[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                if (nums[stack.peek()] == nums[i]) {
                    count[i] += count[stack.peek()];
                }
                stack.pop();
            }
            count[i]++;
            ans += count[i];
            stack.push(i);
        }

        return ans;
    }
}

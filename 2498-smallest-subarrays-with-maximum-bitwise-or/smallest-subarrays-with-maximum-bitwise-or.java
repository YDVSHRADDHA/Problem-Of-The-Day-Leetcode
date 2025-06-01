class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int[] lastSet = new int[32]; // Stores the last index each bit was seen set

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Update lastSet for all bits that are 1 in nums[i]
            for (int b = 0; b < 32; b++) {
                if ((nums[i] & (1 << b)) != 0) {
                    lastSet[b] = i;
                }
            }

            int farthest = i;
            for (int b = 0; b < 32; b++) {
                farthest = Math.max(farthest, lastSet[b]);
            }

            answer[i] = farthest - i + 1;
        }

        return answer;
    }
}

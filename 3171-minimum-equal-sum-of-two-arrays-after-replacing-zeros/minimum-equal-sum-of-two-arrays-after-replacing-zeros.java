class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        long z1 = 0, z2 = 0;

        // Count sum1 and how many 0s in nums1
        for (int num : nums1) {
            if (num == 0) {
                z1++;
                sum1 += 1; // minimum candy to replace 0
            } else {
                sum1 += num;
            }
        }

        // Count sum2 and how many 0s in nums2
        for (int num : nums2) {
            if (num == 0) {
                z2++;
                sum2 += 1;
            } else {
                sum2 += num;
            }
        }

        // If no zeros in nums1 and it's still smaller, we can't fix it
        if (z1 == 0 && sum2 > sum1) return -1;
        
        // If no zeros in nums2 and it's still smaller, we can't fix it
        if (z2 == 0 && sum1 > sum2) return -1;

        // Return the larger of the two sums
        return Math.max(sum1, sum2);
    }
}

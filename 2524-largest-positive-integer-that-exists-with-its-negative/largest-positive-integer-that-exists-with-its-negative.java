class Solution {
     public int findMaxK(int[] nums) {
        int maxPositive = Integer.MIN_VALUE;
        boolean foundPair = false;

        for (int num : nums) {
            if (Arrays.stream(nums).anyMatch(n -> n == -num)) {
                foundPair = true;
                maxPositive = Math.max(maxPositive, Math.abs(num));
            }
        }

        return foundPair ? maxPositive : -1;
    }
}
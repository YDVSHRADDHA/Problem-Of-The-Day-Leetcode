class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] prev = new long[k];

        for (int num : nums) {
            long[] curr = new long[k];

            int numMod = num % k;
            curr[numMod]++; // num itself forms a subarray

            for (int mod = 0; mod < k; mod++) {
                if (prev[mod] > 0) {
                    int newMod = (int)((mod * (long)num) % k);
                    curr[newMod] += prev[mod];
                }
            }

            for (int mod = 0; mod < k; mod++) {
                result[mod] += curr[mod];
            }

            prev = curr; // Move to next
        }

        return result;
    }
}

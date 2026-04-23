import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];

        Map<Integer, List<Integer>> map = new HashMap<>();

        // Step 1: group indices
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Step 2: process each group
        for (List<Integer> list : map.values()) {
            int size = list.size();

            long totalSum = 0;
            for (int idx : list) totalSum += idx;

            long prefixSum = 0;

            for (int m = 0; m < size; m++) {
                int i = list.get(m);

                // left contribution
                long left = (long)i * m - prefixSum;

                // right contribution
                long right = (totalSum - prefixSum - i) - (long)i * (size - m - 1);

                res[i] = left + right;

                prefixSum += i;
            }
        }

        return res;
    }
}
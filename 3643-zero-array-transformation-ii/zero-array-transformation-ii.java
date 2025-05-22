class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
 
        int left = 0, right = queries.length;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canZeroArray(nums, queries, mid)) {
                ans = mid; // try to find smaller k
                right = mid - 1;
            } else {
                left = mid + 1; // need more operations
            }
        }

        return ans;
    }

    private boolean canZeroArray(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] diff = new int[n + 2];

        // Apply first k queries to diff array (difference array technique)
        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            diff[l] += val;
            diff[r + 1] -= val;
        }

        // Use prefix sum to calculate total decrement at each index
        int[] totalDecrement = new int[n];
        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr += diff[i];
            totalDecrement[i] = curr;
        }

        // Check if total decrement is enough to zero each element
        for (int i = 0; i < n; i++) {
            if (totalDecrement[i] < nums[i]) return false;
        }

        return true;
    }
}

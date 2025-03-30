import java.util.*;
//  by gpt!
class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n]; // Stores max jumps from each index
        Arrays.fill(dp, 1); // Minimum jumps from any index is 1 (itself)

        // Store indices sorted by value (smallest to largest)
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> arr[a] - arr[b]); // Sort by arr[i]

        // BFS processing
        for (int i : indices) {
            // Try moving left within range
            for (int j = i - 1; j >= Math.max(0, i - d) && arr[j] < arr[i]; j--) {
                dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            // Try moving right within range
            for (int j = i + 1; j <= Math.min(n - 1, i + d) && arr[j] < arr[i]; j++) {
                dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
        }

        // Maximum value in dp[] is the answer
        int maxJumps = 1;
        for (int jumps : dp) {
            maxJumps = Math.max(maxJumps, jumps);
        }
        return maxJumps;
    }
}

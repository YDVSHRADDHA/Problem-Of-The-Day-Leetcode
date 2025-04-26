import java.util.*;

class Solution {
    private int helper(int mask, int pos, int[] inDegree, List<List<Integer>> adj, int[] score, int[] dp, int n) {
        if (mask == (1 << n) - 1) return 0; // All nodes processed
        if (dp[mask] != -1) return dp[mask]; // If already computed, return the result

        int res = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0 && inDegree[i] == 0) { // If node i is not yet processed and has no incoming edges
                // Mark the node as processed
                for (int ng : adj.get(i)) {
                    inDegree[ng]--; // Reduce in-degree for neighbors
                }

                // Calculate profit for this node
                int val = pos * score[i] + helper(mask | (1 << i), pos + 1, inDegree, adj, score, dp, n);
                res = Math.max(res, val); // Track the maximum profit

                // Restore in-degree for neighbors
                for (int ng : adj.get(i)) {
                    inDegree[ng]++;
                }
            }
        }

        return dp[mask] = res; // Store the result for the current state
    }

    public int maxProfit(int n, int[][] edges, int[] score) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        int[] inDegree = new int[n]; // Array to store the in-degree of nodes
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            inDegree[e[1]]++; // Increment in-degree for the destination node
        }

        int[] dp = new int[1 << n]; // DP array to store the results for all subsets of nodes
        Arrays.fill(dp, -1); // Initialize the DP array with -1 (indicating that no result has been computed yet)

        return helper(0, 1, inDegree, adj, score, dp, n); // Call the helper function with initial values
    }
}

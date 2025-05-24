import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        // Build tree
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        int[] parent = new int[n + 1];
        int[] depth = new int[n + 1];

        // DFS to fill parent and depth
        dfs(1, 0, 0, tree, parent, depth);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int pathLength = getPathLength(u, v, parent, depth);

            if (pathLength == 0) {
                result[i] = 0;
            } else {
                result[i] = modPow(2, pathLength - 1, MOD); // Number of ways to make odd sum
            }
        }

        return result;
    }

    private void dfs(int node, int par, int d, List<List<Integer>> tree, int[] parent, int[] depth) {
        parent[node] = par;
        depth[node] = d;
        for (int nei : tree.get(node)) {
            if (nei != par) {
                dfs(nei, node, d + 1, tree, parent, depth);
            }
        }
    }

    private int getPathLength(int u, int v, int[] parent, int[] depth) {
        int length = 0;
        while (depth[u] > depth[v]) {
            u = parent[u];
            length++;
        }
        while (depth[v] > depth[u]) {
            v = parent[v];
            length++;
        }
        while (u != v) {
            u = parent[u];
            v = parent[v];
            length += 2;
        }
        return length;
    }

    private int modPow(int base, int exp, int mod) {
        long result = 1;
        long b = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * b) % mod;
            }
            b = (b * b) % mod;
            exp >>= 1;
        }
        return (int) result;
    }
}

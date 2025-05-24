 class Solution {
    static final int MOD = 1_000_000_007;
    List<List<Integer>> graph = new ArrayList<>();
    int maxDepth = 0;
    
    public int assignEdgeWeights(int[][] edges) {
  
 
        int n = edges.length + 1;

        // Step 1: Build the graph
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Step 2: DFS to find max depth from node 1
        boolean[] visited = new boolean[n + 1];
        dfs(1, 0, visited);

        // Step 3: Answer = number of ways to assign weights on d edges 
        // so that the total sum is odd = 2^(d-1)
        return modPow(2, maxDepth - 1, MOD);
    }

    private void dfs(int node, int depth, boolean[] visited) {
        visited[node] = true;
        maxDepth = Math.max(maxDepth, depth);
        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                dfs(nei, depth + 1, visited);
            }
        }
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

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0); // start from node 0
        dfs(0, graph, path, res);
        return res;
    }

    private void dfs(int node, int[][] graph, List<Integer> path, List<List<Integer>> res) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(path)); // found a valid path
            return;
        }

        for (int neighbor : graph[node]) {
            path.add(neighbor);            // choose
            dfs(neighbor, graph, path, res); // explore
            path.remove(path.size() - 1);  // un-choose (backtrack)
        }
    }
}

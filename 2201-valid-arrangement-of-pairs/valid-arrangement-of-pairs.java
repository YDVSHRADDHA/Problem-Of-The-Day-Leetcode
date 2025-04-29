import java.util.*;

class Solution {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Integer> outdegree = new HashMap<>();

        // Build the graph and track degrees
        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], x -> new ArrayDeque<>()).add(pair[1]);
            outdegree.put(pair[0], outdegree.getOrDefault(pair[0], 0) + 1);
            indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) + 1);
        }

        // Find the start node
        int start = pairs[0][0];  // default
        for (int key : graph.keySet()) {
            if (outdegree.getOrDefault(key, 0) > indegree.getOrDefault(key, 0)) {
                start = key;
                break;
            }
        }

        List<int[]> result = new ArrayList<>();
        dfs(graph, start, result);

        Collections.reverse(result); // because we build the path backward
        return result.toArray(new int[result.size()][]);
    }

    private void dfs(Map<Integer, Deque<Integer>> graph, int node, List<int[]> result) {
        Deque<Integer> stack = graph.get(node);
        while (stack != null && !stack.isEmpty()) {
            int next = stack.pollFirst();
            dfs(graph, next, result);
            result.add(new int[]{node, next});
        }
    }
}

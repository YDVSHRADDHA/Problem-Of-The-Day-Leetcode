import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0; // Already at the last index

        // Step 1: Build the adjacency map for same value jumps
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        // Step 2: BFS initialization
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        // Step 3: BFS traversal
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int i = queue.poll();

                // If reached the last index, return steps
                if (i == n - 1) return steps;

                // Jump to neighbors (i+1 and i-1)
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                // Jump to all indices with same value
                if (graph.containsKey(arr[i])) {
                    for (int j : graph.get(arr[i])) {
                        if (!visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                    // Remove visited value from the map to optimize performance
                    graph.remove(arr[i]);
                }
            }
            steps++;
        }
        return -1; // Should never reach here
    }
}

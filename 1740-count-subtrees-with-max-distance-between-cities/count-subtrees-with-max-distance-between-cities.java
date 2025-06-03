import java.util.*;

class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0] - 1].add(e[1] - 1);
            graph[e[1] - 1].add(e[0] - 1);
        }

        int[] result = new int[n - 1];

        // Enumerate all subsets
        for (int mask = 1; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) < 2) continue;

            List<Integer> nodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) nodes.add(i);
            }

            if (!isConnected(graph, nodes)) continue;

            int maxDist = 0;
            for (int i = 0; i < nodes.size(); i++) {
                int[] dist = bfs(graph, nodes, nodes.get(i));
                for (int d : dist) {
                    maxDist = Math.max(maxDist, d);
                }
            }
            if (maxDist > 0) result[maxDist - 1]++;
        }

        return result;
    }

    private boolean isConnected(List<Integer>[] graph, List<Integer> nodes) {
        Set<Integer> nodeSet = new HashSet<>(nodes);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(nodes.get(0));
        visited.add(nodes.get(0));

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int nei : graph[curr]) {
                if (nodeSet.contains(nei) && !visited.contains(nei)) {
                    visited.add(nei);
                    queue.offer(nei);
                }
            }
        }

        return visited.size() == nodes.size();
    }

    private int[] bfs(List<Integer>[] graph, List<Integer> nodes, int start) {
        Set<Integer> nodeSet = new HashSet<>(nodes);
        int[] dist = new int[graph.length];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int nei : graph[curr]) {
                if (nodeSet.contains(nei) && dist[nei] == -1) {
                    dist[nei] = dist[curr] + 1;
                    queue.offer(nei);
                }
            }
        }

        return dist;
    }
}

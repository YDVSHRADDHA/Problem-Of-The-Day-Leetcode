import java.util.*;

class Solution {
    public int countPaths(int n, int[][] roads) {
        final int MOD = 1_000_000_007;

        // Step 1: Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2]});
            graph.get(road[1]).add(new int[]{road[0], road[2]});
        }

        // Step 2: Initialize Dijkstra's algorithm
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        long[] shortestTime = new long[n];
        int[] pathCount = new int[n];

        Arrays.fill(shortestTime, Long.MAX_VALUE);
        shortestTime[0] = 0;
        pathCount[0] = 1;

        minHeap.offer(new long[]{0, 0}); // {time, node}

        // Step 3: Process nodes in priority queue
        while (!minHeap.isEmpty()) {
            long[] curr = minHeap.poll();
            long currTime = curr[0];
            int currNode = (int) curr[1];

            if (currTime > shortestTime[currNode]) continue; // Skip outdated times

            for (int[] neighbor : graph.get(currNode)) {
                int nextNode = neighbor[0], travelTime = neighbor[1];

                if (currTime + travelTime < shortestTime[nextNode]) {
                    shortestTime[nextNode] = currTime + travelTime;
                    pathCount[nextNode] = pathCount[currNode];
                    minHeap.offer(new long[]{shortestTime[nextNode], nextNode});
                } else if (currTime + travelTime == shortestTime[nextNode]) {
                    pathCount[nextNode] = (pathCount[nextNode] + pathCount[currNode]) % MOD;
                }
            }
        }

        return pathCount[n - 1];
    }
}

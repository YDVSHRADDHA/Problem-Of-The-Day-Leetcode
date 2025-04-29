import java.util.*;

class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // Step 1: Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // Step 2: BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // [node, currentTime]

        // Track first and second shortest arrival times
        int[][] visit = new int[n + 1][2]; // visit[node][0] = min, [1] = 2nd min
        for (int i = 0; i <= n; i++) Arrays.fill(visit[i], -1);

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int currTime = curr[1];

            if (visit[node][0] == -1) visit[node][0] = currTime;
            else if (visit[node][1] == -1 && visit[node][0] != currTime) {
                visit[node][1] = currTime;
            } else {
                continue;
            }

            for (int neighbor : graph.get(node)) {
                int nextTime = currTime;

                // If in red signal period, wait for green
                if ((nextTime / change) % 2 == 1) {
                    nextTime += change - (nextTime % change);
                }

                nextTime += time;
                queue.offer(new int[]{neighbor, nextTime});
            }
        }

        return visit[n][1]; // Second minimum arrival time to node n
    }
}

public class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] visited = new boolean[n];
        int maxCycleLength = -1;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            Map<Integer, Integer> nodeToStep = new HashMap<>();
            int step = 0;
            int current = i;

            while (current != -1) {
                if (nodeToStep.containsKey(current)) {
                    // Cycle found
                    maxCycleLength = Math.max(maxCycleLength, step - nodeToStep.get(current));
                    break;
                }

                if (visited[current]) break;

                nodeToStep.put(current, step++);
                visited[current] = true;
                current = edges[current];
            }
        }

        return maxCycleLength;
    }
}

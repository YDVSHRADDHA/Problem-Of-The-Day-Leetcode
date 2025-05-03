class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Step 1: Build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<String, Double> probMap = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];

            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[]{v, i});
            graph.computeIfAbsent(v, x -> new ArrayList<>()).add(new int[]{u, i});
        }

        // Step 2: Max heap based on probability
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        double[] maxProb = new double[n]; // maxProb[i] = max probability to reach node i

        pq.offer(new double[]{1.0, start}); // [probability, node]
        maxProb[start] = 1.0;

        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            double prob = curr[0];
            int node = (int) curr[1];

            if (node == end) return prob;

            for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                int next = neighbor[0];
                int edgeIndex = neighbor[1];
                double newProb = prob * succProb[edgeIndex];

                if (newProb > maxProb[next]) {
                    maxProb[next] = newProb;
                    pq.offer(new double[]{newProb, next});
                }
            }
        }

        return 0.0;
    }
}

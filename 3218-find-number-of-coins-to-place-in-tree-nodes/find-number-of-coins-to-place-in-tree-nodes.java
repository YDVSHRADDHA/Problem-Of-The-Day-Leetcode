 

class Solution {
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) tree.add(new ArrayList<>());

        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        long[] res = new long[n];
        dfs(0, -1, tree, cost, res);
        return res;
    }

    // Helper class to hold top max 3 and bottom min 2 values
    private static class Pair {
        List<Integer> maxVals;
        List<Integer> minVals;
        int size;

        Pair(List<Integer> maxVals, List<Integer> minVals, int size) {
            this.maxVals = maxVals;
            this.minVals = minVals;
            this.size = size;
        }
    }

    private Pair dfs(int node, int parent, List<List<Integer>> tree, int[] cost, long[] res) {
        List<Integer> maxVals = new ArrayList<>();
        List<Integer> minVals = new ArrayList<>();
        int subtreeSize = 1;

        for (int child : tree.get(node)) {
            if (child == parent) continue;
            Pair childRes = dfs(child, node, tree, cost, res);
            subtreeSize += childRes.size;
            merge(maxVals, childRes.maxVals, 3, false); // max values
            merge(minVals, childRes.minVals, 2, true);  // min values
        }

        maxVals.add(cost[node]);
        minVals.add(cost[node]);
        Collections.sort(maxVals, Collections.reverseOrder());
        Collections.sort(minVals);

        if (maxVals.size() > 3) maxVals = maxVals.subList(0, 3);
        if (minVals.size() > 2) minVals = minVals.subList(0, 2);

        if (subtreeSize < 3) {
            res[node] = 1;
        } else {
            long maxProduct = 0;
            if (maxVals.size() >= 3) {
                maxProduct = Math.max(maxProduct, 1L * maxVals.get(0) * maxVals.get(1) * maxVals.get(2));
            }
            if (minVals.size() >= 2 && maxVals.size() >= 1) {
                maxProduct = Math.max(maxProduct, 1L * minVals.get(0) * minVals.get(1) * maxVals.get(0));
            }
            res[node] = Math.max(maxProduct, 0);
        }

        return new Pair(maxVals, minVals, subtreeSize);
    }

    private void merge(List<Integer> base, List<Integer> add, int limit, boolean ascending) {
        base.addAll(add);
        if (ascending) Collections.sort(base);
        else base.sort(Collections.reverseOrder());
        if (base.size() > limit) base.subList(limit, base.size()).clear();
    }
}

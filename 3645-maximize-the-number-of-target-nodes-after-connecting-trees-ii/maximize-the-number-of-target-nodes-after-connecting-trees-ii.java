class Solution {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1; // number of nodes in first tree
        int m = edges2.length + 1; // number of nodes in second tree

        int[] color1 = new int[n]; // stores color (0 or 1) for each node in tree1
        int[] color2 = new int[m]; // stores color (0 or 1) for each node in tree2

        // Count of color0 and color1 nodes in tree1 and tree2 respectively
        int[] count1 = build(edges1, color1); // returns [count_color0, count_color1] for tree1
        int[] count2 = build(edges2, color2); // returns [count_color0, count_color1] for tree2

        int[] res = new int[n]; // answer for each node in tree1

        for (int i = 0; i < n; i++) {
            // For each node in tree1:
            // Get its color, count how many nodes in tree1 have that color
            // Add the maximum group size from tree2 (either color0 or color1)
            res[i] = count1[color1[i]] + Math.max(count2[0], count2[1]);
        }

        return res;
    }

    // Builds the tree and colors nodes using DFS. Returns the count of nodes of each color
    private int[] build(int[][] edges, int[] color) {
        int n = edges.length + 1;

        // Adjacency list to represent the tree
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        // Add edges to the graph (undirected)
        for (int[] edge : edges) {
            children.get(edge[0]).add(edge[1]);
            children.get(edge[1]).add(edge[0]);
        }

        // DFS to assign colors: color[node] = depth % 2
        int color0Count = dfs(0, -1, 0, children, color); // count of nodes with color 0
        return new int[] { color0Count, n - color0Count }; // total nodes - color0 = color1
    }

    // DFS traversal to color nodes and count how many are color 0
    private int dfs(
        int node,
        int parent,
        int depth,
        List<List<Integer>> children,
        int[] color
    ) {
        int isColor0 = 1 - (depth % 2); // color 0 if depth even, else 0
        color[node] = depth % 2;

        int count = isColor0; // this node is color0? count += 1
        for (int child : children.get(node)) {
            if (child != parent) {
                count += dfs(child, node, depth + 1, children, color);
            }
        }
        return count;
    }
}

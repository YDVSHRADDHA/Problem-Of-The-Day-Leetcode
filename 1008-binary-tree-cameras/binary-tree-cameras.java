class Solution {
    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        // If the root itself needs a camera, add one
        if (dfs(root) == 0) {
            cameras++;
        }
        return cameras;
    }

    // Helper function with DFS
    // Returns:
    // 0 → needs camera
    // 1 → has camera
    // 2 → covered
    private int dfs(TreeNode node) {
        if (node == null) return 2; // Null nodes are considered covered

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == 0 || right == 0) {
            // If any child needs a camera, place camera here
            cameras++;
            return 1;
        }

        if (left == 1 || right == 1) {
            // If any child has a camera, we’re covered
            return 2;
        }

        // Else, we need a camera
        return 0;
    }
}

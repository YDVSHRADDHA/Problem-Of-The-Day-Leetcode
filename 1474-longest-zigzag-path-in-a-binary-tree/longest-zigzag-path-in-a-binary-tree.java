class Solution {
    int maxLength = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root.left, true, 1);  // Start by going left
        dfs(root.right, false, 1); // Start by going right
        return maxLength;
    }

    private void dfs(TreeNode node, boolean isLeft, int length) {
        if (node == null) return;

        maxLength = Math.max(maxLength, length);

        if (isLeft) {
            dfs(node.left, true, 1);          // Restart ZigZag if same direction
            dfs(node.right, false, length + 1); // Continue ZigZag
        } else {
            dfs(node.right, false, 1);         // Restart ZigZag if same direction
            dfs(node.left, true, length + 1);  // Continue ZigZag
        }
    }
}

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    private Pair dfs(TreeNode node) {
        if (node == null) return new Pair(0, null); // Base Case: If node is null, return depth 0 and null node.

        Pair left = dfs(node.left);  // Recursively go left
        Pair right = dfs(node.right); // Recursively go right

        if (left.depth == right.depth) {
            return new Pair(left.depth + 1, node);  // If both sides are equal, this is the LCA.
        }
        return (left.depth > right.depth) ? new Pair(left.depth + 1, left.node) 
                                          : new Pair(right.depth + 1, right.node);
    }

    private static class Pair {
        int depth;
        TreeNode node;
        Pair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
}

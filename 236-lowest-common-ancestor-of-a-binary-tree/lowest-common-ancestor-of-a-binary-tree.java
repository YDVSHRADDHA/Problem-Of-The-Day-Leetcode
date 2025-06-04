class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: If root is null or matches either p or q, return root
        if (root == null || root == p || root == q) return root;

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right return non-null, current root is LCA
        if (left != null && right != null) return root;

        // Otherwise, return the non-null subtree
        return left != null ? left : right;
    }
}

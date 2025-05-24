/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int leftCount = 0;
    private int rightCount = 0;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // Count subtree sizes and identify x
        countSubtreeNodes(root, x);

        // Nodes not in the subtree of node x (i.e., parent side)
        int parentCount = n - (leftCount + rightCount + 1);

        // The second player wins if they can control more than half the tree
        return Math.max(parentCount, Math.max(leftCount, rightCount)) > n / 2;
    }

    private int countSubtreeNodes(TreeNode node, int x) {
        if (node == null) return 0;

        int left = countSubtreeNodes(node.left, x);
        int right = countSubtreeNodes(node.right, x);

        if (node.val == x) {
            leftCount = left;
            rightCount = right;
        }

        return left + right + 1;
    }
}

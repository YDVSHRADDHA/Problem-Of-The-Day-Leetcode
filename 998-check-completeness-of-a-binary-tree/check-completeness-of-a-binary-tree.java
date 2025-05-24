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
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false; // flag to mark when a null child is seen
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            if (current == null) {
                // Once a null is seen, all following nodes must be null for completeness
                end = true;
            } else {
                if (end) {
                    // If we have seen a null previously, and now see a non-null node, tree is not complete
                    return false;
                }
                // Add children to queue for BFS
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        
        return true;
    }
}

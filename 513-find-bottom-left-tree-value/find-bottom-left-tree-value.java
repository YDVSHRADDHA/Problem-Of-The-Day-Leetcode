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
    // private int maxDepth;
    // private int bottomLeftValue;
    // public int findBottomLeftValue(TreeNode root) {
    //     // DFS
    //     maxDepth = -1;
    //     bottomLeftValue = 0;
    //     dfs(root,0);

    //     return bottomLeftValue;
    // }

    // private void dfs(TreeNode current, int depth){
    //     if(current == null){
    //         return;
    //     }
    //     if(depth>maxDepth){
    //         // if true, we discovered a new level
    //         maxDepth = depth;
    //         bottomLeftValue =current.val;
    //     }
    //     dfs(current.left, depth+1);
    //     dfs(current.right, depth+1);
    

    // BFS
   public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = root;
        queue.add(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.right != null) {
                queue.add(current.right);
            }
            if (current.left != null) {
                queue.add(current.left);
            }
        }
        return current.val;
    }
}
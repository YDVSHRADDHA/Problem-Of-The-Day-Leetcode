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


import java.util.*;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            
            if (node.right != null) stack.push(node.right); // Push right first
            if (node.left != null) stack.push(node.left);   // Push left second
        }

        return result;
    }
}
















// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         ArrayList<Integer> arr = new ArrayList<>();
//         if(root==null)
//             return arr;
        
// //        Preorder Traversal  (Root Left Right) 
//         arr.add(root.val);
//         arr.addAll(preorderTraversal(root.left));
//         arr.addAll(preorderTraversal(root.right));
        
//         return arr;
//     }
// }
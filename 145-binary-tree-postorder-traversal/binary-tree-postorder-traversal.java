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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisited = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    root = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }
}

// import java.util.*;

// class Solution {
//     public List<Integer> postorderTraversal(TreeNode root) {
//         // List to store the postorder traversal result
//         List<Integer> result = new ArrayList<>();
        
//         // Base case: if the tree is empty, return the empty result list
//         if (root == null) return result;

//         // Stack1 is used to traverse the tree
//         Stack<TreeNode> stack1 = new Stack<>();
//         // Stack2 is used to store nodes in reverse postorder
//         Stack<TreeNode> stack2 = new Stack<>();

//         // Push the root node onto Stack1
//         stack1.push(root);

//         // Process all nodes until Stack1 is empty
//         while (!stack1.isEmpty()) {
//             // Pop a node from Stack1
//             TreeNode node = stack1.pop();
//             // Push the node onto Stack2
//             stack2.push(node);

//             // If the left child exists, push it onto Stack1
//             if (node.left != null) {
//                 stack1.push(node.left);
//             }

//             // If the right child exists, push it onto Stack1
//             if (node.right != null) {
//                 stack1.push(node.right);
//             }
//         }

//         // Stack2 now contains the nodes in reverse postorder
//         // Pop nodes from Stack2 and add them to the result list
//         while (!stack2.isEmpty()) {
//             result.add(stack2.pop().val);
//         }

//         // Return the postorder traversal result
//         return result;
//     }
// }

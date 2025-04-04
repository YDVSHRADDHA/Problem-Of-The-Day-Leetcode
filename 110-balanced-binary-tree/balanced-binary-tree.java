import java.util.*;

// TreeNode class representing a node in the Binary Tree
class TreeNode {
    int val;          // Value of the node
    TreeNode left;    // Left child
    TreeNode right;   // Right child

    // Constructor to initialize node value
    TreeNode(int x) { val = x; }
}



//  Solution class containing the function to check if the tree is balanced
class Solution {
    
    // Main function to check if a tree is balanced
    public boolean isBalanced(TreeNode root) {
        // Calls helper function `checkHeight`
        // If the function returns -1, it means the tree is unbalanced
        return checkHeight(root) != -1;
    }

    //  Helper function to check the height of the tree
    private int checkHeight(TreeNode node) {
        // Base Case: If node is null, return height 0
        if (node == null) return 0;

        //  Recursively find the height of the left subtree
        int leftHeight = checkHeight(node.left);

        // If left subtree is unbalanced, return -1 immediately
        if (leftHeight == -1) return -1;

        //  Recursively find the height of the right subtree
        int rightHeight = checkHeight(node.right);

        // If right subtree is unbalanced, return -1 immediately
        if (rightHeight == -1) return -1;

        //  Check balance condition: Difference in height should not be more than 1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Tree is unbalanced
        }

        //  Return the height of the current node (1 + max(leftHeight, rightHeight))
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

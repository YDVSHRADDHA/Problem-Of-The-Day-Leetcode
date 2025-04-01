class Solution {
    public int rob(TreeNode root) {
        // Helper function to perform recursion and return the max value for each subtree
        int[] result = robSubtree(root);
        return Math.max(result[0], result[1]);  // return the maximum of robbing or not robbing the root
    }
    
    // Helper function that returns an array where:
    // result[0] = max money if we do not rob the current node
    // result[1] = max money if we rob the current node
    private int[] robSubtree(TreeNode node) {
        if (node == null) {
            return new int[2];  // Base case: no node, so both are 0
        }
        
        // Recurse on left and right children
        int[] left = robSubtree(node.left);
        int[] right = robSubtree(node.right);
        
        // If we don't rob this node, we take the max of robbing or not robbing the left and right children
        int[] result = new int[2];
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // Don't rob current node
        
        // If we rob this node, we can't rob its children, so we add its value + robbing the grandchildren
        result[1] = node.val + left[0] + right[0]; // Rob current node, don't rob its children
        
        return result;
    }
}

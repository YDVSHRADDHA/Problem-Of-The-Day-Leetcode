class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);  // go left
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key); // go right
        } else {
            // Node to delete is found
            if (root.left == null) return root.right;  // only right child or no child
            if (root.right == null) return root.left;  // only left child

            // Node with two children
            TreeNode minNode = findMin(root.right);  // inorder successor
            root.val = minNode.val;                  // copy its value
            root.right = deleteNode(root.right, minNode.val);  // delete successor
        }

        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}

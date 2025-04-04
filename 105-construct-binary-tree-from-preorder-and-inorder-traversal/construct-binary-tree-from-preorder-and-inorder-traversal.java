//  Basic Concepts:
// Preorder: Root → Left → Right

// Inorder: Left → Root → Right

// Using preorder to know the root, and inorder to split the tree into left and right subtrees.
class Solution {
   public TreeNode buildTree(int[] preorder, int[] inorder) {
    // Step 1: Map to store value -> index for inorder (for quick lookup)
    Map<Integer, Integer> inMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }

    // Step 2: Call helper to build the tree recursively
    return helper(preorder, 0, preorder.length - 1,
                  inorder, 0, inorder.length - 1, inMap);
}

private TreeNode helper(int[] preorder, int preStart, int preEnd,
                        int[] inorder, int inStart, int inEnd,
                        Map<Integer, Integer> inMap) {
    // Base case: if no elements to process
    if (preStart > preEnd || inStart > inEnd) return null;

    // Step 3: The first element in preorder is always the root
    TreeNode root = new TreeNode(preorder[preStart]);

    // Step 4: Get the root’s index from the inorder array
    int inRoot = inMap.get(root.val);

    // Step 5: Count how many elements are in the left subtree
    int numsLeft = inRoot - inStart;

    // Step 6: Recursively build left and right subtrees
    root.left = helper(preorder, preStart + 1, preStart + numsLeft,
                       inorder, inStart, inRoot - 1, inMap);

    root.right = helper(preorder, preStart + numsLeft + 1, preEnd,
                        inorder, inRoot + 1, inEnd, inMap);

    return root;
}

}
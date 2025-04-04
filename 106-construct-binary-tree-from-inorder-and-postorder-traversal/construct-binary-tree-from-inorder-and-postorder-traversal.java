// Basic Concepts:
// Postorder: Left → Right → Root

// Inorder: Left → Root → Right

// The last element in postorder is the root.

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    // Step 1: Store value -> index from inorder for quick lookup
    Map<Integer, Integer> inMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }

    // Step 2: Call recursive function
    return helper(inorder, 0, inorder.length - 1,
                  postorder, 0, postorder.length - 1, inMap);
}

private TreeNode helper(int[] inorder, int inStart, int inEnd,
                        int[] postorder, int postStart, int postEnd,
                        Map<Integer, Integer> inMap) {
    // Base case
    if (inStart > inEnd || postStart > postEnd) return null;

    // Step 3: Root is last element in postorder
    TreeNode root = new TreeNode(postorder[postEnd]);

    // Step 4: Find the root in inorder
    int inRoot = inMap.get(root.val);

    // Step 5: Elements count in the left subtree
    int numsLeft = inRoot - inStart;

    // Step 6: Recursively build left and right subtrees
    root.left = helper(inorder, inStart, inRoot - 1,
                       postorder, postStart, postStart + numsLeft - 1, inMap);

    root.right = helper(inorder, inRoot + 1, inEnd,
                        postorder, postStart + numsLeft, postEnd - 1, inMap);

    return root;
}

}
//  Recursive Approach (Clean & Simple)
 class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true; // Empty tree is symmetric
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;  // Both are null → mirror
        if (t1 == null || t2 == null) return false; // Only one is null → not mirror

        // Check values and recursive mirror of opposite children
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }
}
// --------------------------------------------------------------------------------------

// \U0001f501 Iterative Approach (Using Queue)

// import java.util.*;

// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode(int x) { val = x; }
// }

// class Solution {
//     public boolean isSymmetric(TreeNode root) {
//         if (root == null) return true;

//         // Queue to hold pairs of nodes to compare
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.add(root.left);
//         queue.add(root.right);

//         while (!queue.isEmpty()) {
//             // Pop two nodes for comparison
//             TreeNode t1 = queue.poll();
//             TreeNode t2 = queue.poll();

//             // Both null means symmetric at this level
//             if (t1 == null && t2 == null) continue;

//             // One null or values don’t match means not symmetric
//             if (t1 == null || t2 == null || t1.val != t2.val) return false;

//             // Add children in mirrored order for comparison
//             queue.add(t1.left);  // left of t1
//             queue.add(t2.right); // right of t2

//             queue.add(t1.right); // right of t1
//             queue.add(t2.left);  // left of t2
//         }

//         return true;
//     }
// }

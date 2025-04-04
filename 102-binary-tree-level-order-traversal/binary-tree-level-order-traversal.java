import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result; // If tree is empty, return empty list.

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // Start with root node.

        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level.
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll(); // Remove node from queue.
                level.add(node.val); // Store value of current node.

                // Add left and right children to queue if they exist.
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level); // Add the level to the final result.
        }
        return result;
    }
}

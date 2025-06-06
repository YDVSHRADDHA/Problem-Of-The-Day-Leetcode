import java.util.ArrayList;
import java.util.List;

public class Solution {
    
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);
        return buildBalancedBST(nodes, 0, nodes.size() - 1);
    }
    
    // Step 1: Inorder traversal to get sorted node values
    private void inorder(TreeNode root, List<Integer> nodes) {
        if (root == null) return;
        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
    }
    
    // Step 2: Build balanced BST from sorted list
    private TreeNode buildBalancedBST(List<Integer> nodes, int start, int end) {
        if (start > end) return null;
        
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nodes.get(mid));
        
        node.left = buildBalancedBST(nodes, start, mid - 1);
        node.right = buildBalancedBST(nodes, mid + 1, end);
        
        return node;
    }
}

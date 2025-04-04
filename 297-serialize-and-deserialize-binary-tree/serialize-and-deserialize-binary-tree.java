// https://www.youtube.com/watch?v=0Rm2cajspwY&list=RDGMEMCMFH2exzjBeE_zAHHJOdxg&index=50

// "Some dreams are silent whispers of the heart, waiting to find their voice." \U0001f3b6✨ ~ Sapne Re \U0001f319\U0001f499

import java.util.*;

public class Codec {

    // **Encodes a tree to a single string.**
    public String serialize(TreeNode root) {
        if (root == null) return "null";  //  If tree is empty, return "null"
        
        StringBuilder sb = new StringBuilder(); //  StringBuilder to store serialized result
        Queue<TreeNode> queue = new LinkedList<>(); //  Queue for BFS traversal
        queue.offer(root); //  Start BFS with the root node
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //  Get the current node
            
            if (node == null) {
                sb.append("null,");  //  Store "null" for missing nodes
            } else {
                sb.append(node.val).append(","); //  Append node value to result
                queue.offer(node.left);  //  Add left child to queue (even if null)
                queue.offer(node.right); //  Add right child to queue (even if null)
            }
        }
        return sb.toString();  //  Return the final serialized string
    }

    // **Decodes your encoded data to tree.**
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null; //  If the string is "null", return an empty tree
        
        String[] values = data.split(","); //  Split the serialized string into an array
        Queue<TreeNode> queue = new LinkedList<>(); //  Queue for BFS reconstruction
        
        TreeNode root = new TreeNode(Integer.parseInt(values[0])); //  Create the root node
        queue.offer(root); //  Add root to queue
        
        int i = 1; //  Start from the second element in the array
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //  Get the current node from queue
            
            //  Process left child
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i])); //  Create left child
                queue.offer(node.left); //  Add left child to queue
            }
            i++; //  Move to the next element
            
            //  Process right child
            if (!values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i])); //  Create right child
                queue.offer(node.right); //  Add right child to queue
            }
            i++; //  Move to the next element
        }
        return root; //  Return the reconstructed tree
    }
}

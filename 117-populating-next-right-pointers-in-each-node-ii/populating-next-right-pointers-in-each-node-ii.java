class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        
        Node curr = root;
        
        while (curr != null) {
            Node dummy = new Node(0); // dummy head for the next level
            Node prev = dummy;
            
            // Traverse the current level
            while (curr != null) {
                if (curr.left != null) {
                    prev.next = curr.left;
                    prev = prev.next;
                }
                if (curr.right != null) {
                    prev.next = curr.right;
                    prev = prev.next;
                }
                curr = curr.next; // move to next node in current level
            }
            
            curr = dummy.next; // move to the next level
        }
        
        return root;
    }
}

import java.util.Random;

class Skiplist {
    // Define the maximum level of the Skiplist
    private static final int MAX_LEVEL = 16;
    
    // Define the head node, which is a dummy node to simplify insertion/deletion
    private Node head;
    
    // Random object to decide the level of the node to insert
    private Random rand;

    public Skiplist() {
        // Initialize the head node and the random generator
        head = new Node(-1, MAX_LEVEL);
        rand = new Random();
    }

    // Node definition: Each node stores a value and next pointers for multiple levels
    private static class Node {
        int val;
        Node[] next;
        
        public Node(int val, int level) {
            this.val = val;
            this.next = new Node[level];
        }
    }

    // Search operation
    public boolean search(int target) {
        Node curr = head;
        // Traverse from the highest level to the lowest level
        for (int level = MAX_LEVEL - 1; level >= 0; level--) {
            // Move forward as long as we don't exceed the target value
            while (curr.next[level] != null && curr.next[level].val < target) {
                curr = curr.next[level];
            }
            // If the node matches the target, return true
            if (curr.next[level] != null && curr.next[level].val == target) {
                return true;
            }
        }
        return false; // If not found
    }

    // Add operation
    public void add(int num) {
        // Create an array of nodes to track where to insert
        Node[] update = new Node[MAX_LEVEL];
        Node curr = head;
        
        // Traverse from the highest level to the lowest level
        for (int level = MAX_LEVEL - 1; level >= 0; level--) {
            while (curr.next[level] != null && curr.next[level].val < num) {
                curr = curr.next[level];
            }
            update[level] = curr; // Store the node where we stopped at each level
        }
        
        // Randomly decide the level for the new node
        int level = randomLevel();
        Node newNode = new Node(num, level);
        
        // Insert the new node into the skiplist at the appropriate levels
        for (int i = 0; i < level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // Erase operation
    public boolean erase(int num) {
        Node[] update = new Node[MAX_LEVEL];
        Node curr = head;
        
        // Traverse from the highest level to the lowest level
        for (int level = MAX_LEVEL - 1; level >= 0; level--) {
            while (curr.next[level] != null && curr.next[level].val < num) {
                curr = curr.next[level];
            }
            update[level] = curr; // Store the node where we stopped at each level
        }
        
        // Check if the target exists and remove it
        curr = curr.next[0];
        if (curr != null && curr.val == num) {
            // Remove the node at all levels
            for (int i = 0; i < MAX_LEVEL; i++) {
                if (update[i].next[i] == curr) {
                    update[i].next[i] = curr.next[i];
                }
            }
            return true;
        }
        return false; // If the element is not found
    }

    // Helper function to generate a random level for a node (determining probability)
    private int randomLevel() {
        int level = 1;
        while (rand.nextDouble() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }
}

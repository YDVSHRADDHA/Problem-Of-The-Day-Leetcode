import java.util.*;

class DinnerPlates {
    private HashMap<Integer, Stack<Integer>> hashmap; // For storing the data
    private PriorityQueue<Integer> pq; // For storing the indexes of the leftmost empty stacks (min heap)
    private int capacity, index;
    
    public DinnerPlates(int capacity) {
        hashmap = new HashMap<>();
        pq = new PriorityQueue<>();
        this.capacity = capacity;
        this.index = -1;
    }
    
    // Push the value into the leftmost available stack
    public void push(int val) {
        // If there are available empty stacks in the min-heap (pq), try to use the leftmost one
        while (!pq.isEmpty() && pq.peek() > index) {
            pq.poll(); // Remove invalid indices that are larger than the current rightmost stack
        }

        // If no leftmost empty stack exists, push to the rightmost stack
        if (pq.isEmpty()) {
            if (index == -1 || hashmap.get(index).size() == capacity) {
                index++; // Create a new stack
            }
            hashmap.putIfAbsent(index, new Stack<>());
            hashmap.get(index).push(val);
        } else {
            // Use the leftmost empty stack
            int leftIndex = pq.poll();
            hashmap.get(leftIndex).push(val);
        }
    }
    
    // Pop the value from the rightmost non-empty stack
    public int pop() {
        // Ensure index points to the rightmost non-empty stack
        while (index >= 0 && (!hashmap.containsKey(index) || hashmap.get(index).isEmpty())) {
            index--; // Move to the next non-empty stack
        }
        
        // If no non-empty stack exists
        if (index == -1) {
            return -1;
        }
        
        // Pop the value from the current rightmost stack
        int val = hashmap.get(index).pop();
        
        // If the current stack is empty, move to the leftmost stack
        if (hashmap.get(index).isEmpty()) {
            index--;
        }
        
        return val;
    }
    
    // Pop the value from the stack at a specific index
    public int popAtStack(int idx) {
        // If the stack at idx is empty or doesn't exist, return -1
        if (!hashmap.containsKey(idx) || hashmap.get(idx).isEmpty()) {
            return -1;
        }
        
        // After popping from this stack, it becomes empty, so we add the index to the min-heap (pq)
        pq.add(idx);
        
        return hashmap.get(idx).pop();
    }
}

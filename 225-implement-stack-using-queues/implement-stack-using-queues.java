import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        q2.add(x); // Step 1: Insert into q2
        while (!q1.isEmpty()) {
            q2.add(q1.poll()); // Step 2: Move all from q1 to q2
        }
        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    public int pop() {
        return q1.poll(); // Remove front element
    }
    
    public int top() {
        return q1.peek(); // Get front element
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}

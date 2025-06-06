import java.util.Stack;

class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        
        for (String op : ops) {
            switch (op) {
                case "C":
                    stack.pop();
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                case "+":
                    int top = stack.pop();
                    int newTop = top + stack.peek();
                    stack.push(top);        // Put the popped element back
                    stack.push(newTop);     // Push the sum
                    break;
                default:
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }
        
        int sum = 0;
        for (int score : stack) {
            sum += score;
        }
        return sum;
    }
}

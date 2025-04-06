import java.util.Stack;

class Solution {
    public int calculate(String s) {
        int result = 0; // Final result
        int sign = 1;   // Current sign (+1 or -1)
        int num = 0;    // Current number being formed
        Stack<Integer> stack = new Stack<>(); // To store previous result & sign

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Build the number digit by digit
                num = num * 10 + (ch - '0');
            } else if (ch == '+') {
                // Add previous number with its sign
                result += sign * num;
                num = 0;
                sign = 1; // Next sign is positive
            } else if (ch == '-') {
                result += sign * num;
                num = 0;
                sign = -1; // Next sign is negative
            } else if (ch == '(') {
                // Save current state before new bracket starts
                stack.push(result);
                stack.push(sign);

                // Reset for inner bracket
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                // Add last number before closing bracket
                result += sign * num;
                num = 0;

                // Multiply with last sign and add previous result
                result *= stack.pop(); // sign
                result += stack.pop(); // result
            }
        }

        // If number still left at the end
        result += sign * num;

        return result;
    }
}

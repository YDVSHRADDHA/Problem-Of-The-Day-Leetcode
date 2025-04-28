import java.util.*;

class Solution {
    public String countOfAtoms(String A) {
        // Stack to hold the element count maps at each level of parentheses
        Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        // Stack to hold the multipliers for each level of parentheses
        Deque<Integer> numStack = new ArrayDeque<>();
        // StringBuilder for constructing element names
        StringBuilder sb = new StringBuilder();
        // StringBuilder for accumulating numbers
        StringBuilder nsb = new StringBuilder();
        
        // Push initial empty map to stack to handle outermost level
        stack.push(new HashMap<>());
        
        // Iterate through the string from right to left
        for (int i = A.length() - 1; i >= 0; i--) {
            char c = A.charAt(i);
            
            if (c == ')') {
                // On encountering ')', push a new map and save the multiplier
                stack.push(new HashMap<>());
                numStack.push(getNum(nsb)); // Parse the number
                nsb.setLength(0); // Reset the number builder
            } else if (c == '(') {
                // On encountering '(', pop the current map and multiply by the number
                Map<String, Integer> top = stack.pop();
                int mul = numStack.pop();
                for (String key : top.keySet()) {
                    // Multiply element counts by the multiplier and merge back
                    stack.peek().merge(key, top.get(key) * mul, Integer::sum);
                }
            } else if (Character.isDigit(c)) {
                // If the character is a digit, accumulate it in nsb
                nsb.append(c);
            } else if (Character.isLowerCase(c)) {
                // If the character is a lowercase letter, append to sb
                sb.append(c);
            } else { // Upper case letter, start of a new element name
                sb.append(c);
                // Convert the element name and add it to the current map
                String element = sb.reverse().toString();
                stack.peek().merge(element, getNum(nsb), Integer::sum);
                nsb.setLength(0); // Reset number builder
                sb.setLength(0); // Reset element name builder
            }
        }

        // Collect the final map of elements and their counts
        Map<String, Integer> res = stack.pop();
        List<String> atoms = new ArrayList<>(res.keySet());
        
        // Sort elements lexicographically
        Collections.sort(atoms);
        
        // Build the result string
        StringBuilder result = new StringBuilder();
        for (String key : atoms) {
            result.append(key);
            // Append the count if it's greater than 1
            if (res.get(key) > 1) {
                result.append(res.get(key));
            }
        }
        
        return result.toString();
    }

    // Helper function to parse numbers
    private int getNum(StringBuilder sb) {
        return sb.isEmpty() ? 1 : Integer.parseInt(sb.reverse().toString());
    }
}

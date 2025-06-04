class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> toRemove = new HashSet<>();

        // First pass: track invalid parentheses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    toRemove.add(i);
                }
            }
        }

        // Any unmatched '(' left in the stack are also invalid
        while (!stack.isEmpty()) {
            toRemove.add(stack.pop());
        }

        // Second pass: build valid string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}

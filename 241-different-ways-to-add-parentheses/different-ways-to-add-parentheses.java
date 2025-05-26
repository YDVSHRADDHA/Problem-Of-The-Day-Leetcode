class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
 
   
    List<Integer> result = new ArrayList<>();

    // Go through every character
    for (int i = 0; i < expression.length(); i++) {
        char ch = expression.charAt(i);

        // If it's an operator
        if (ch == '+' || ch == '-' || ch == '*') {
            // Divide into left and right
            String leftPart = expression.substring(0, i);
            String rightPart = expression.substring(i + 1);

            // Recursively solve left and right
            List<Integer> leftResults = diffWaysToCompute(leftPart);
            List<Integer> rightResults = diffWaysToCompute(rightPart);

            // Combine each left and right using current operator
            for (int left : leftResults) {
                for (int right : rightResults) {
                    if (ch == '+') result.add(left + right);
                    if (ch == '-') result.add(left - right);
                    if (ch == '*') result.add(left * right);
                }
            }
        }
    }

    // If input is a number with no operators
    if (result.isEmpty()) {
        result.add(Integer.parseInt(expression));
    }

    return result;
}

}
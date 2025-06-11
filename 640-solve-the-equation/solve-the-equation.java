public class Solution {
    public String solveEquation(String equation) {
        String[] sides = equation.split("=");
        int[] left = parse(sides[0]);
        int[] right = parse(sides[1]);

        int coef = left[0] - right[0];
        int constVal = right[1] - left[1];

        if (coef == 0) {
            if (constVal == 0) return "Infinite solutions";
            else return "No solution";
        }

        return "x=" + (constVal / coef);
    }

    // returns [coefficient of x, constant sum]
    private int[] parse(String expr) {
        int coef = 0, constant = 0, i = 0, sign = 1;
        int n = expr.length();

        while (i < n) {
            if (expr.charAt(i) == '+') {
                sign = 1;
                i++;
            } else if (expr.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            int num = 0;
            boolean hasNum = false;

            while (i < n && Character.isDigit(expr.charAt(i))) {
                num = num * 10 + (expr.charAt(i) - '0');
                i++;
                hasNum = true;
            }

            if (i < n && expr.charAt(i) == 'x') {
                coef += sign * (hasNum ? num : 1);
                i++;
            } else {
                constant += sign * num;
            }
        }

        return new int[]{coef, constant};
    }
}

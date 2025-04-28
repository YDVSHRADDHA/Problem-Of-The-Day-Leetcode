class Solution {
    public int scoreOfStudents(String s, int[] answers) {
        StringBuilder numbers = new StringBuilder();
        StringBuilder operators = new StringBuilder();
        int len = s.length();
        
        // Separate numbers and operators
        for(int idx = 0; idx < len; idx++) {
            char ch = s.charAt(idx);
            if(ch == '+' || ch == '*') 
                operators.append(ch);
            else 
                numbers.append(ch);
        }
        
        len = numbers.length();
        
        // dp[start][end] stores possible results from numbers[start] to numbers[end]
        HashSet<Integer>[][] dp = new HashSet[len][len]; 
        for(int row = 0; row < len; row++) {
            for(int col = 0; col < len; col++) 
                dp[row][col] = new HashSet();
        }
        
        // DP to compute possible results for each sub-expression
        for(int gap = 0; gap < len; gap++) { 
            for(int start = 0, end = gap; end < len; start++, end++) {
                if(gap == 0) {
                    dp[start][end].add(numbers.charAt(start) - '0');
                } else {
                    for(int operatorIdx = start; operatorIdx <= end - 1; operatorIdx++) {
                        HashSet<Integer> leftSet = dp[start][operatorIdx];
                        HashSet<Integer> rightSet = dp[operatorIdx + 1][end];
                        char operator = operators.charAt(operatorIdx);

                        // Combine left and right sub-results
                        for(int leftEval : leftSet) {
                            for(int rightEval : rightSet) {
                                int eval = (operator == '+') ? leftEval + rightEval : leftEval * rightEval;
                                // Prune results that exceed the expected range
                                if(eval <= 1000) 
                                    dp[start][end].add(eval);
                            }
                        }
                    }
                }
            }
        }
        
        // Collect all evaluations from dp[0][len - 1]
        HashSet<Integer> evaluations = dp[0][len - 1];
        
        // Calculate the correct answer using standard precedence rules
        int correctAns = getCorrectAns(s);
        
        // Grade the students' answers
        int totalScore = 0;
        for(int answer : answers) {
            if(answer == correctAns) 
                totalScore += 5;
            else if(evaluations.contains(answer)) 
                totalScore += 2;
        }
        
        return totalScore;
    }
    
    private int getCorrectAns(String s) {
        Stack<Integer> numberStack = new Stack();
        Stack<Character> operatorStack = new Stack();
        
        for(int idx = 0; idx < s.length(); idx++) {
            char ch = s.charAt(idx);
            if(ch == '+' || ch == '*') {
                while(!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(ch)) {
                    char operator = operatorStack.pop();
                    int numB = numberStack.pop();
                    int numA = numberStack.pop();
                    int eval = evaluate(numA, numB, operator);
                    numberStack.push(eval);
                }
                operatorStack.push(ch);
            } else {
                numberStack.push(ch - '0');
            }
        }
        
        // Final evaluations after all operators are processed
        while(!operatorStack.isEmpty()) {
            char operator = operatorStack.pop();
            int numB = numberStack.pop();
            int numA = numberStack.pop();
            int eval = evaluate(numA, numB, operator);
            numberStack.push(eval);
        }
        
        return numberStack.pop();
    }
    
    private int precedence(char operator) {
        return (operator == '+') ? 1 : 2; // '*' has higher precedence than '+'
    }
    
    private int evaluate(int numA, int numB, char operator) {
        return (operator == '+') ? numA + numB : numA * numB;
    }
}

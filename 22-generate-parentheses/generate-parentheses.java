class Solution {
    public List<String> generateParenthesis(int n) {
        
        List<String> ans = new ArrayList<>();
        
        // Start backtracking
        backtrack(ans, "", 0, 0, n);
        
        return ans;
    }
    
    // open → count of '(' used so far
    // close → count of ')' used so far
    // n → total pairs of ()
    private void backtrack(List<String> ans, String current, int open, int close, int n){
        
        // Base Case: If the current string length is 2 * n → valid combination found
        if(current.length() == 2 * n){
            ans.add(current);
            return;
        }
        
        // Condition 1: Add '(' if open < n
        if(open < n){
            backtrack(ans, current + "(", open + 1, close, n);
        }
        
        // Condition 2: Add ')' if close < open → To balance only if there is '(' available
        if(close < open){
            backtrack(ans, current + ")", open, close + 1, n);
        }
    }
}

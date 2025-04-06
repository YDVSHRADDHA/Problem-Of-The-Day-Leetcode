class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] fact = new int[n + 1];
        
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
            fact[i] = fact[i - 1] * i;
        }
        
        StringBuilder ans = new StringBuilder();
        k--;  // for 0-based indexing
        
        helper(numbers, fact, n, k, ans);
        
        return ans.toString();
    }
    
    private void helper(List<Integer> numbers, int[] fact, int n, int k, StringBuilder ans) {
        if (n == 0) return;
        
        int index = k / fact[n - 1];
        ans.append(numbers.get(index));
        numbers.remove(index);
        
        k = k % fact[n - 1];
        
        helper(numbers, fact, n - 1, k, ans);
    }
}

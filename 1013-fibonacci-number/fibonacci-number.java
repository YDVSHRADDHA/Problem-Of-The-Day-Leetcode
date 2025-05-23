// class Solution {
//     public int fib(int n) {
 
//         if (n <= 1) return n;
//         return fib(n - 1) + fib(n - 2);
//     }
// }


// class Solution {
//     public int fib(int n) {
//         int[] memo = new int[n + 1];
//         Arrays.fill(memo, -1);
//         return helper(n, memo);
//     }

//     private int helper(int n, int[] memo) {
//         if (n <= 1) return n;
//         if (memo[n] != -1) return memo[n];
//         memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
//         return memo[n];
//     }
// }


class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}

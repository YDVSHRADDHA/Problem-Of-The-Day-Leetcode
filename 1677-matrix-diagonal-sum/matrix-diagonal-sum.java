class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // Add primary diagonal
            sum += mat[i][i];
            // Add secondary diagonal
            sum += mat[i][n - i - 1];
        }

        // If n is odd, subtract the center element (it got added twice)
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }
}

class Solution {
    private static final int MOD = 1_000_000_007;

 

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[][] transition = new int[26][26];

        // Build transition matrix
        for (int i = 0; i < 26; i++) {
            int steps = nums.get(i);
            for (int j = 1; j <= steps; j++) {
                int nextChar = (i + j) % 26;
                transition[i][nextChar] = (transition[i][nextChar] + 1) % MOD;
            }
        }

        // Matrix exponentiation: transition^t
        int[][] powered = matrixPower(transition, t);

        // Base vector: initial character frequencies
        int[] base = new int[26];
        for (char c : s.toCharArray()) {
            base[c - 'a']++;
        }

        // Multiply base vector by transition^t
        long total = 0;
        for (int i = 0; i < 26; i++) {
            long count = 0;
            for (int j = 0; j < 26; j++) {
                count = (count + (1L * base[j] * powered[j][i]) % MOD) % MOD;
            }
            total = (total + count) % MOD;
        }

        return (int) total;
    }

    // Matrix exponentiation: A^power
    private int[][] matrixPower(int[][] matrix, int power) {
        int size = matrix.length;
        int[][] result = new int[size][size];

        // Initialize result as identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            power >>= 1;
        }

        return result;
    }

    // Multiply two matrices
    private int[][] multiply(int[][] A, int[][] B) {
        int size = A.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    result[i][j] = (int) ((result[i][j] + 1L * A[i][k] * B[k][j]) % MOD);
                }
            }
        }

        return result;
    }
}

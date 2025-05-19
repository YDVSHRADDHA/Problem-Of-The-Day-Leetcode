public class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            HashSet<Integer> rowSet = new HashSet<>();
            HashSet<Integer> colSet = new HashSet<>();

            for (int j = 0; j < n; j++) {
                // Check row
                if (!rowSet.add(matrix[i][j])) {
                    return false; // Duplicate in row
                }

                // Check column
                if (!colSet.add(matrix[j][i])) {
                    return false; // Duplicate in column
                }
            }
        }

        return true;
    }
}

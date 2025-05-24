class Solution {
    
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long sumAbs = 0;
        int minAbs = Integer.MAX_VALUE;
        int negCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                if (val < 0) {
                    negCount++;
                }
                int absVal = Math.abs(val);
                sumAbs += absVal;
                if (absVal < minAbs) {
                    minAbs = absVal;
                }
            }
        }

        if (negCount % 2 == 0) {
            return sumAbs;
        } else {
            return sumAbs - 2L * minAbs;
        }
    }
}

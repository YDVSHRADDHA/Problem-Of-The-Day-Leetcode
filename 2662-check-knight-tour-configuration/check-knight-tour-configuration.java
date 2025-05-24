public class Solution {
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        int total = n * n;

        // Create an array to store the coordinates of each move
        int[][] positions = new int[total][2];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                positions[grid[i][j]] = new int[]{i, j};
            }
        }

        // The knight must start at (0, 0)
        if (positions[0][0] != 0 || positions[0][1] != 0)
            return false;

        // Check each move is a valid knight move
        for (int i = 1; i < total; i++) {
            int r1 = positions[i - 1][0], c1 = positions[i - 1][1];
            int r2 = positions[i][0], c2 = positions[i][1];
            int dr = Math.abs(r1 - r2), dc = Math.abs(c1 - c2);

            if (!((dr == 2 && dc == 1) || (dr == 1 && dc == 2))) {
                return false;
            }
        }

        return true;
    }
}

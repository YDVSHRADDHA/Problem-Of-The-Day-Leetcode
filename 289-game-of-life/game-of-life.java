class Solution {
    public void gameOfLife(int[][] board) {
   
    int m = board.length, n = board[0].length;
    int[][] directions = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},          {0, 1},
        {1, -1},  {1, 0},  {1, 1}
    };

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int liveNeighbors = 0;

            for (int[] dir : directions) {
                int ni = i + dir[0];
                int nj = j + dir[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    // Only consider current state (LSB)
                    liveNeighbors += board[ni][nj] & 1;
                }
            }

            // Rule 1 & 3: live cell dies
            if ((board[i][j] & 1) == 1 && (liveNeighbors == 2 || liveNeighbors == 3)) {
                board[i][j] |= 2; // Set 2nd bit to 1 → stays alive
            }

            // Rule 4: dead cell becomes live
            if ((board[i][j] & 1) == 0 && liveNeighbors == 3) {
                board[i][j] |= 2; // Set 2nd bit to 1 → becomes alive
            }
        }
    }

    // Final update: keep only next state
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            board[i][j] >>= 1;
        }
    }

}
}

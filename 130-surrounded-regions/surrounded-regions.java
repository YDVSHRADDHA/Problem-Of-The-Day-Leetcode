class Solution {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        
        // Add border 'O's to the queue
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') queue.offer(new int[]{i, 0});
            if (board[i][n - 1] == 'O') queue.offer(new int[]{i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') queue.offer(new int[]{0, j});
            if (board[m - 1][j] == 'O') queue.offer(new int[]{m - 1, j});
        }

        // Directions: up, down, left, right
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // BFS to mark safe 'O's
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];

            if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') continue;

            board[x][y] = 'T'; // mark as safe

            for (int[] dir : dirs) {
                queue.offer(new int[]{x + dir[0], y + dir[1]});
            }
        }

        // Final pass
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';     // captured
                else if (board[i][j] == 'T') board[i][j] = 'O'; // restore
            }
        }
    }
}

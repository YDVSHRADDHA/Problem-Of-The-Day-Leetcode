class Solution {
    public int minimumObstacles(int[][] grid) { 
        int m = grid.length, n = grid[0].length;

        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        int[][] cost = new int[m][n];
        for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);
        cost[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int x = curr[0], y = curr[1];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    int newCost = cost[x][y] + grid[nx][ny];
                    if (newCost < cost[nx][ny]) {
                        cost[nx][ny] = newCost;
                        if (grid[nx][ny] == 1) {
                            deque.offerLast(new int[]{nx, ny});
                        } else {
                            deque.offerFirst(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        return cost[m - 1][n - 1];
    }
}

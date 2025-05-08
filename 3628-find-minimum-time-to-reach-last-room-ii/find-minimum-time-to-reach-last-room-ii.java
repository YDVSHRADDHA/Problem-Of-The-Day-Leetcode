class Solution {
    public int minTimeToReach(int[][] moveTime) {   
        int n = moveTime.length, m = moveTime[0].length;
        int[][][] dp = new int[n][m][2];
        for (int[][] row : dp)
            for (int[] cell : row)
                Arrays.fill(cell, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dp[0][0][0] = 0;
        pq.offer(new int[]{0, 0, 0, 0}); // time, row, col, parity

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], x = curr[1], y = curr[2], parity = curr[3];
            if (x == n-1 && y == m-1) return time;

            if (dp[x][y][parity] < time) continue; // already found better path

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    int moveCost = (parity == 0) ? 1 : 2;
                    int waitTime = Math.max(time, moveTime[nx][ny]);
                    int newTime = waitTime + moveCost;
                    int nextParity = 1 - parity;

                    if (newTime < dp[nx][ny][nextParity]) {
                        dp[nx][ny][nextParity] = newTime;
                        pq.offer(new int[]{newTime, nx, ny, nextParity});
                    }
                }
            }
        }

        return -1; // unreachable (though per constraints, this should not happen)
    }
}

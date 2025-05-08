import java.util.*;

public class Solution {
    public int minimumTime(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][1] > 1 && grid[1][0] > 1) return -1; // Edge case: stuck

        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        int[][] minTime = new int[m][n];
        for (int[] row : minTime)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0}); // time, row, col
        minTime[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], r = curr[1], c = curr[2];

            if (r == m - 1 && c == n - 1)
                return time;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= m || nc >= n)
                    continue;

                int arrival = time + 1;
                int cellTime = grid[nr][nc];

                if (arrival < cellTime) {
                    // need to wait — parity check
                    int wait = cellTime;
                    if ((wait - arrival) % 2 == 1)
                        wait++; // wait an extra second if parity doesn't match
                    arrival = wait;
                }

                if (arrival < minTime[nr][nc]) {
                    minTime[nr][nc] = arrival;
                    pq.offer(new int[]{arrival, nr, nc});
                }
            }
        }

        return -1;
    }
}

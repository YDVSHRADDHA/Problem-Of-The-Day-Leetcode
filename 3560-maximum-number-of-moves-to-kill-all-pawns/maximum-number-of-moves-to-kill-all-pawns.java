import java.util.*;

public class Solution {
    int[][] distances;
    int[][] knightMoves = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1},
                           {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};
    int[][] memo;
    int pawnCount;

    public int maxMoves(int kx, int ky, int[][] positions) {
        pawnCount = positions.length;
        distances = new int[pawnCount + 1][pawnCount];
        memo = new int[1 << pawnCount][pawnCount + 1]; // +1 for starting position
        for (int[] row : memo) Arrays.fill(row, -1);

        // Precompute knight move distances from starting position to all pawns
        for (int i = 0; i < pawnCount; i++) {
            distances[pawnCount][i] = bfs(kx, ky, positions[i][0], positions[i][1]);
        }

        // Precompute knight move distances between every pair of pawns
        for (int i = 0; i < pawnCount; i++) {
            for (int j = 0; j < pawnCount; j++) {
                if (i != j) {
                    distances[i][j] = bfs(positions[i][0], positions[i][1],
                                          positions[j][0], positions[j][1]);
                }
            }
        }

        return dfs((1 << pawnCount) - 1, pawnCount, true); // All pawns available, starting from knight
    }

    private int dfs(int mask, int currPos, boolean isAliceTurn) {
        if (mask == 0) return 0; // No pawns left

        if (memo[mask][currPos] != -1) return memo[mask][currPos];

        int best = isAliceTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < pawnCount; i++) {
            if ((mask & (1 << i)) != 0) {
                int nextMask = mask ^ (1 << i);
                int moveCost = distances[currPos][i];
                int next = dfs(nextMask, i, !isAliceTurn);

                if (isAliceTurn) {
                    best = Math.max(best, moveCost + next);
                } else {
                    best = Math.min(best, moveCost + next);
                }
            }
        }

        memo[mask][currPos] = best;
        return best;
    }

    private int bfs(int sx, int sy, int ex, int ey) {
        if (sx == ex && sy == ey) return 0;
        boolean[][] visited = new boolean[50][50];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], d = curr[2];

            for (int[] move : knightMoves) {
                int nx = x + move[0], ny = y + move[1];
                if (nx >= 0 && ny >= 0 && nx < 50 && ny < 50 && !visited[nx][ny]) {
                    if (nx == ex && ny == ey) return d + 1;
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, d + 1});
                }
            }
        }

        return Integer.MAX_VALUE; // unreachable, though should not happen
    }
}

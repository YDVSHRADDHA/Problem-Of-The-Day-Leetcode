import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1]; // To mark visited cells
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // Start from square 1
        visited[1] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all positions for current dice roll
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) return moves; // Reached the last square

                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (next > n * n) break;

                    int[] pos = getCoordinates(next, n);
                    int row = pos[0];
                    int col = pos[1];

                    // Check if it's a snake or ladder
                    if (board[row][col] != -1) {
                        next = board[row][col];
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            moves++; // One dice roll done
        }

        return -1; // Cannot reach the end
    }

    // Helper function to convert 1D label to 2D grid coordinates
    private int[] getCoordinates(int label, int n) {
        int row = (label - 1) / n;
        int col = (label - 1) % n;

        int actualRow = n - 1 - row;
        int actualCol = row % 2 == 0 ? col : n - 1 - col;

        return new int[]{actualRow, actualCol};
    }
}

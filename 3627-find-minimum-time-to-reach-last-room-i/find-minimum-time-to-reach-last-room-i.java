import java.util.*;

// Main class to solve the problem using Dijkstra's algorithm
class Solution {
    // Static method to compute the minimum time to reach the bottom-right cell
    public static int minTimeToReach(int[][] moveTime) {
        int r = moveTime.length, c = moveTime[0].length;

        // This matrix tracks the minimum time to reach each cell
        int[][] minimumTime = new int[r][c];
        for (int[] row : minimumTime) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Priority queue (min-heap) to explore cells by their earliest reachable time
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.steps - b.steps);

        // Start from cell (0, 0) at time -1 (we'll increment before checking next)
        pq.add(new Pair(-1, 0, 0));
        minimumTime[0][0] = 0;

        // Dijkstra-like traversal
        while (!pq.isEmpty()) {
            Pair top = pq.poll();
            int i = top.i, j = top.j;
            int nextStep = top.steps + 1;

            // Explore all four directions
            if (i + 1 < r) update(i + 1, j, pq, nextStep, moveTime, minimumTime);
            if (i - 1 >= 0) update(i - 1, j, pq, nextStep, moveTime, minimumTime);
            if (j + 1 < c) update(i, j + 1, pq, nextStep, moveTime, minimumTime);
            if (j - 1 >= 0) update(i, j - 1, pq, nextStep, moveTime, minimumTime);

            // Early exit if we have reached the bottom-right cell
            if (minimumTime[r - 1][c - 1] != Integer.MAX_VALUE) 
                return minimumTime[r - 1][c - 1] + 1;
        }

        // Return the final time to reach the target cell (+1 for final move)
        return minimumTime[r - 1][c - 1] + 1;
    }

    // Helper method to update the minimum time and push new state into the queue
    static void update(int i, int j, PriorityQueue<Pair> pq, int nextStep, int[][] moveTime, int[][] minimumTime) {
        // Wait until moveTime[i][j] if needed
        if (nextStep < moveTime[i][j]) nextStep = moveTime[i][j];

        // Only update if this path is faster
        if (minimumTime[i][j] > nextStep) {
            pq.add(new Pair(nextStep, i, j));
            minimumTime[i][j] = nextStep;
        }
    }
}

// Helper class to represent a cell and the time taken to reach it
class Pair {
    int steps;  // Time taken to reach this cell
    int i, j;   // Coordinates of the cell

    public Pair(int steps, int i, int j) {
        this.steps = steps;
        this.i = i;
        this.j = j;
    }
}

class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        
           // Directions array representing East, South, West, North
        int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[][] traversed = new int[rows * cols][2]; // To store the visited cells
        int idx = 0; // To track the number of visited cells

        // Initial step size is 1, value of direction represents the current direction
        for (int step = 1, direction = 0; idx < rows * cols; ++step) {
            // Loop over two iterations, each corresponds to a pair of direction moves
            for (int i = 0; i < 2; ++i) {
                // Move in the current direction for 'step' times
                for (int j = 0; j < step; ++j) {
                    // Validate the current position within the matrix boundaries
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        traversed[idx][0] = rStart;
                        traversed[idx][1] = cStart;
                        idx++;
                    }
                    // Update the current position
                    rStart += dir[direction][0];
                    cStart += dir[direction][1];
                }
                // Change direction in a circular manner: East -> South -> West -> North
                direction = (direction + 1) % 4;
            }
        }
        return traversed;
    }
}
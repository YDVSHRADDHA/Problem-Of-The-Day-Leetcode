class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            ArrayList<Integer> elems = new ArrayList<>();

            int top = layer;
            int left = layer;
            int bottom = m - layer - 1;
            int right = n - layer - 1;

            // top row
            for (int j = left; j <= right; j++) {
                elems.add(grid[top][j]);
            }

            // right column
            for (int i = top + 1; i < bottom; i++) {
                elems.add(grid[i][right]);
            }

            // bottom row
            for (int j = right; j >= left; j--) {
                elems.add(grid[bottom][j]);
            }

            // left column
            for (int i = bottom - 1; i > top; i--) {
                elems.add(grid[i][left]);
            }

            // rotate
            int size = elems.size();
            int r = k % size;

            ArrayList<Integer> rotated = new ArrayList<>();

            for (int i = r; i < size; i++) {
                rotated.add(elems.get(i));
            }

            for (int i = 0; i < r; i++) {
                rotated.add(elems.get(i));
            }

            int idx = 0;

            // put back top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(idx++);
            }

            // put back right column
            for (int i = top + 1; i < bottom; i++) {
                grid[i][right] = rotated.get(idx++);
            }

            // put back bottom row
            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }

            // put back left column
            for (int i = bottom - 1; i > top; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}
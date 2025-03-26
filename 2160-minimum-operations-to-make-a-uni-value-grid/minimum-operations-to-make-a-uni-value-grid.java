import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        int m = grid.length, n = grid[0].length;

        // Flatten the grid into a 1D list
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }

        // Check if transformation is possible
        int remainder = list.get(0) % x;
        for (int num : list) {
            if (num % x != remainder) return -1; // Impossible case
        }

        // Sort the list and find the median
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        int operations = 0;

        // Compute total operations required to make all elements equal to the median
        for (int num : list) {
            operations += Math.abs(num - median) / x;
        }

        return operations;
    }
}

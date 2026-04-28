import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        // Flatten grid
        for (int[] row : grid) {
            for (int val : row) {
                list.add(val);
            }
        }
        
        // Check feasibility
        int mod = list.get(0) % x;
        for (int val : list) {
            if (val % x != mod) return -1;
        }
        
        // Sort
        Collections.sort(list);
        
        // Median
        int median = list.get(list.size() / 2);
        
        // Count operations
        int ops = 0;
        for (int val : list) {
            ops += Math.abs(val - median) / x;
        }
        
        return ops;
    }
}
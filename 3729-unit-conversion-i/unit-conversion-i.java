import java.util.*;

class Solution {
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length + 1; // Number of units is one more than the number of conversions
        int MOD = 1000000007;
        
        // Step 1: Create an adjacency list to represent the graph
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Step 2: Populate the graph with the given conversions
        for (int[] conversion : conversions) {
            int source = conversion[0];
            int target = conversion[1];
            int factor = conversion[2];
            
            // Add both directions since we can go from source to target and target to source
            graph[source].add(new int[]{target, factor});
            graph[target].add(new int[]{source, 1 / factor}); // The reverse conversion (inverse factor)
        }
        
        // Step 3: Use BFS or DFS to compute the conversion from unit 0 to all other units
        long[] baseUnitConversion = new long[n];
        Arrays.fill(baseUnitConversion, -1); // Initialize with -1 (indicating unvisited)
        baseUnitConversion[0] = 1; // Base unit is 1 for itself
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        // Step 4: BFS traversal to calculate the conversions
        while (!queue.isEmpty()) {
            int currentUnit = queue.poll();
            
            for (int[] neighbor : graph[currentUnit]) {
                int neighborUnit = neighbor[0];
                int conversionFactor = neighbor[1];
                
                if (baseUnitConversion[neighborUnit] == -1) { // If the unit has not been visited
                    baseUnitConversion[neighborUnit] = (baseUnitConversion[currentUnit] * conversionFactor) % MOD;
                    queue.add(neighborUnit);
                }
            }
        }
        
        // Step 5: Return the result array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (int) baseUnitConversion[i];
        }
        
        return result;
    }
}

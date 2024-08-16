import java.util.List;

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        // Initialize globalMin with the smallest element of the first array
        int globalMin = arrays.get(0).get(0);
        
        // Initialize globalMax with the largest element of the first array
        int globalMax = arrays.get(0).get(arrays.get(0).size() - 1);
        
        // Initialize maxDistance to store the maximum distance found so far
        int maxDistance = 0;

        // Iterate through the arrays starting from the second array (index 1)
        for (int i = 1; i < arrays.size(); i++) {
            // Get the smallest element in the current array
            int currMin = arrays.get(i).get(0);
            
            // Get the largest element in the current array
            int currMax = arrays.get(i).get(arrays.get(i).size() - 1);

            // Calculate the potential maximum distance by comparing:
            // 1. The current array's largest element (currMax) with the global minimum so far (globalMin)
            // 2. The current array's smallest element (currMin) with the global maximum so far (globalMax)
            maxDistance = Math.max(maxDistance, Math.abs(currMax - globalMin));
            maxDistance = Math.max(maxDistance, Math.abs(globalMax - currMin));

            // Update globalMin with the smallest value found so far across all processed arrays
            globalMin = Math.min(globalMin, currMin);
            
            // Update globalMax with the largest value found so far across all processed arrays
            globalMax = Math.max(globalMax, currMax);
        }

        // Return the maximum distance found
        return maxDistance;
    }
}

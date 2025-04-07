class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        
        // Step 1: Calculate total sum of array
        for(int num : nums) sum += num;
        
        // Step 2: If sum is not divisible by k → can't divide equally
        if(sum % k != 0) return false;
        
        int target = sum / k;  // Each subset must sum to target
        
        Arrays.sort(nums);    // Step 3: Sorting for optimization
        
        int n = nums.length;
        
        // Step 4: If largest element > target → Not possible
        if(nums[n-1] > target) return false;
        
        boolean[] visited = new boolean[n];  // Track used elements
        
        // Step 5: Call backtracking function
        return backtrack(nums, visited, k, 0, 0, target);
    }
    
    
    // Backtracking function to try partitioning
    private boolean backtrack(int[] nums, boolean[] visited, int k, int currSum, int start, int target){
        
        // Step 6: If only 1 subset left → It will automatically have target sum
        if(k == 1) return true;
        
        // Step 7: If current subset sum is complete → Move to next subset
        if(currSum == target){
            return backtrack(nums, visited, k-1, 0, 0, target);
        }
        
        // Step 8: Try adding unvisited elements to current subset
        for(int i=start; i<nums.length; i++){
            
            // Skip if already used
            if(visited[i]) continue;
            
            // Skip if adding nums[i] exceeds target sum
            if(currSum + nums[i] > target) continue;
            
            // Choose element
            visited[i] = true;
            
            // Explore further
            if(backtrack(nums, visited, k, currSum + nums[i], i+1, target)){
                return true;
            }
            
            // Backtrack (undo choice)
            visited[i] = false;
        }
        
        return false;  // No valid partition found
    }
}

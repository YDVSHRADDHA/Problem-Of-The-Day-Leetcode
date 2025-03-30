class Solution {
    public int partitionDisjoint(int[] nums) {
        
        int partitionIdx = 0; 
        int maxLeft = nums[0]; 
        int globalMax = nums[0]; 

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < maxLeft) { 
                partitionIdx = i; 
                maxLeft = globalMax; 
            } else {
                globalMax = Math.max(globalMax, nums[i]); 
            }
        }
        return partitionIdx + 1; 
    
    }
}
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        
        Arrays.sort(nums);  // Sort array to easily handle duplicates
        
        backtrack(0, nums, new ArrayList<>(), ans);
        
        return ans;
    }
    
    private void backtrack(int index, int[] nums, List<Integer> temp, List<List<Integer>> ans) {
        
        // Add current subset to answer
        ans.add(new ArrayList<>(temp));
        
        // Explore all possibilities starting from 'index'
        for(int i = index; i < nums.length; i++) {
            
            // Skip duplicates in the same level
            if(i > index && nums[i] == nums[i-1]) continue;
            
            temp.add(nums[i]);  // Include nums[i] in subset
            backtrack(i + 1, nums, temp, ans);  // Recurse
            
            temp.remove(temp.size() - 1);  // Backtrack
        }
    }
}

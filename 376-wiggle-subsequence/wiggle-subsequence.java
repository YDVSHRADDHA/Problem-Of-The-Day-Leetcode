class Solution {
    public int wiggleMaxLength(int[] nums) {
       
    if (nums.length < 2) return nums.length;
    
    int count = 1;
    int prevDiff = 0, currDiff = 0;
    
    for (int i = 1; i < nums.length; i++) {
        currDiff = nums[i] - nums[i - 1];
        
        if ((currDiff > 0 && prevDiff <= 0) || (currDiff < 0 && prevDiff >= 0)) {
            count++;
            prevDiff = currDiff;
        }
    }
    
    return count;

    }
}
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int exceptedSum = n*(n+1)/2;
        int actualSum = 0;

        for(int num : nums){
            actualSum +=num;
        }
        return exceptedSum - actualSum;
    }
}
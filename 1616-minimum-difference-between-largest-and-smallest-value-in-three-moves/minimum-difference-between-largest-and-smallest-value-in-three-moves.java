class Solution {
    public int minDifference(int[] nums) {
        int numsS = nums.length;

        if(numsS <= 4)
        return 0;

          Arrays.sort(nums);
          int rslt = Integer.MAX_VALUE;

        //   FOUR CONDITIONS WILL BE :

        for(int l = 0,r = numsS-4; l <4; l++, r++ ){
            rslt = Math.min(rslt, nums[r]-nums[l]);
        }

        return rslt;
    }
}
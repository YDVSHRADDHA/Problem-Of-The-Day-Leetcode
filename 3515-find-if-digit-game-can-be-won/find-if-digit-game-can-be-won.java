class Solution {
    public boolean canAliceWin(int[] nums) {
   
        int singleSum = 0, doubleSum = 0;

        for (int num : nums) {
            if (num < 10) {
                singleSum += num;
            } else if (num < 100) {
                doubleSum += num;
            }
        }

        return singleSum != doubleSum;
    }
}

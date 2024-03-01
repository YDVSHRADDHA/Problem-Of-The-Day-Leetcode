class Solution {
    public int findPeakElement(int[] nums) {
    //   Binary Search : an algorithm that runs in O(log n) time.
    int left = 0 , right = nums.length-1;

    while(left < right){
        int mid = (left + right)/2;
        if(nums[mid] > nums[mid+1]){
            right = mid;
        }
        else{
            left = mid + 1;
        }
    }
    return left;
}
}
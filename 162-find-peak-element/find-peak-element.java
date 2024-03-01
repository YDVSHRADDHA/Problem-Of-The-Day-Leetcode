class Solution {
    public int findPeakElement(int[] nums) {
       int peak= findPeakDivideConquer(nums, 0, nums.length - 1);
       return peak;
}
 public static int findPeakDivideConquer(int[] arr, int left, int right) {
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;

        if (arr[mid] > arr[mid + 1]) {
            return findPeakDivideConquer(arr, left, mid);
        } else {
            return findPeakDivideConquer(arr, mid + 1, right);
        }
    }
}
 
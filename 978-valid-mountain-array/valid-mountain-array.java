class Solution {
   public boolean validMountainArray(int[] arr) {
    if (arr.length < 3) return false;

    int left = 0, right = arr.length - 1;

    // Move left pointer to the peak
    while (left < right && arr[left] < arr[left + 1]) {
        left++;
    }

    // Move right pointer to the peak
    while (left < right && arr[right] < arr[right - 1]) {
        right--;
    }

    return left == right && left != 0 && right != arr.length - 1;
}

}
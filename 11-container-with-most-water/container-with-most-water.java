class Solution {

    public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
        int minHeight = Math.min(height[left], height[right]);
        int width = right - left;
        maxArea = Math.max(maxArea, minHeight * width);

        if (height[left] < height[right]) {
            left++;  // Move the left pointer to the right
        } else {
            right--;  // Move the right pointer to the left
        }
    }

    return maxArea;
}

}

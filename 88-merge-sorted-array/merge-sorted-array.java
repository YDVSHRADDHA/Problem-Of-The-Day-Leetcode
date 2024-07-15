class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int n1 = 0, n2 = 0;
        int[] result = new int[m + n];
        int k = 0;

        while (n1 < m && n2 < n) {
            if (nums1[n1] < nums2[n2]) {
                result[k++] = nums1[n1++];
            } else {
                result[k++] = nums2[n2++];
            }
        }

        // Copy remaining elements of nums1, if any
        while (n1 < m) {
            result[k++] = nums1[n1++];
        }

        // Copy remaining elements of nums2, if any
        while (n2 < n) {
            result[k++] = nums2[n2++];
        }

        // Copy the result back to nums1
        for (int i = 0; i < m + n; i++) {
            nums1[i] = result[i];
        }

        //  return nums1;
    }
}

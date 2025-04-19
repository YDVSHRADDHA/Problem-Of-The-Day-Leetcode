public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] result = new int[k];
        
        // Try all possible splits of picking i elements from nums1 and k-i elements from nums2
        for (int i = Math.max(0, k - n2); i <= Math.min(k, n1); i++) {
            int[] seq1 = maxSubsequence(nums1, i); // Get largest subsequence from nums1
            int[] seq2 = maxSubsequence(nums2, k - i); // Get largest subsequence from nums2
            
            // Merge the two subsequences
            int[] merged = merge(seq1, seq2);
            
            // Update the result if we found a larger number
            if (compare(merged, 0, result, 0) > 0) {
                result = merged;
            }
        }
        
        return result;
    }

    // Function to get the largest subsequence of length k from an array
    private int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] stack = new int[k];
        int top = 0;
        
        for (int i = 0; i < n; i++) {
            while (top > 0 && stack[top - 1] < nums[i] && (n - i + top) > k) {
                top--;
            }
            if (top < k) {
                stack[top++] = nums[i];
            }
        }
        
        return stack;
    }

    // Merge two subsequences
    private int[] merge(int[] seq1, int[] seq2) {
        int m = seq1.length, n = seq2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, index = 0;
        
        while (i < m || j < n) {
            if (i < m && j < n) {
                if (compare(seq1, i, seq2, j) > 0) {
                    merged[index++] = seq1[i++];
                } else {
                    merged[index++] = seq2[j++];
                }
            } else if (i < m) {
                merged[index++] = seq1[i++];
            } else {
                merged[index++] = seq2[j++];
            }
        }
        
        return merged;
    }

    // Compare two subsequences to decide which one is larger
    private int compare(int[] seq1, int i, int[] seq2, int j) {
        while (i < seq1.length && j < seq2.length && seq1[i] == seq2[j]) {
            i++;
            j++;
        }
        if (j == seq2.length) {
            return 1;
        }
        if (i == seq1.length) {
            return -1;
        }
        return seq1[i] - seq2[j];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;
        
        int[] result = solution.maxNumber(nums1, nums2, k);
        System.out.println(Arrays.toString(result));
    }
}

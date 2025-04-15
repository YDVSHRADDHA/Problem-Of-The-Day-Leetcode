class Solution {
    class SegmentTree {
        int[] tree;
        int size;

        SegmentTree(int n) {
            size = n;
            tree = new int[2 * n];
        }

        // Increment value at index
        void update(int index, int value) {
            index += size;
            tree[index] += value;
            while (index > 1) {
                index /= 2;
                tree[index] = tree[2 * index] + tree[2 * index + 1];
            }
        }

        // Query sum in range [left, right)
        int query(int left, int right) {
            int result = 0;
            left += size;
            right += size;
            while (left < right) {
                if ((left & 1) == 1) result += tree[left++];
                if ((right & 1) == 1) result += tree[--right];
                left >>= 1;
                right >>= 1;
            }
            return result;
        }
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Map nums2[i] to its index
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        // Transform nums1 based on position in nums2
        int[] transformed = new int[n];
        for (int i = 0; i < n; i++) {
            transformed[i] = pos[nums1[i]];
        }

        // Count of elements to the left that are smaller
        SegmentTree leftTree = new SegmentTree(n);
        int[] leftCount = new int[n];
        for (int i = 0; i < n; i++) {
            leftCount[i] = leftTree.query(0, transformed[i]);
            leftTree.update(transformed[i], 1);
        }

        // Count of elements to the right that are larger
        SegmentTree rightTree = new SegmentTree(n);
        int[] rightCount = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            rightCount[i] = rightTree.query(transformed[i] + 1, n);
            rightTree.update(transformed[i], 1);
        }

        // Calculate good triplets
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += (long) leftCount[i] * rightCount[i];
        }

        return total;
    }
}

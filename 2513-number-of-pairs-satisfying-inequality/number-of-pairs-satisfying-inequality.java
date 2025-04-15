import java.util.*;

public class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] arr = new int[n];
        TreeSet<Integer> set = new TreeSet<>();

        // Step 1: Compute arr[i] = nums1[i] - nums2[i]
        for (int i = 0; i < n; i++) {
            arr[i] = nums1[i] - nums2[i];
            set.add(arr[i]);
            set.add(arr[i] + diff);
        }

        // Step 2: Coordinate Compression
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int num : set) {
            map.put(num, idx++);
        }

        // Step 3: Initialize Segment Tree
        SegmentTree segTree = new SegmentTree(idx);

        // Step 4: Iterate through arr and count pairs
        long count = 0;
        for (int i = 0; i < n; i++) {
            int target = arr[i] + diff;
            int compressedTarget = map.get(target);
            count += segTree.query(0, compressedTarget);
            int compressedIndex = map.get(arr[i]);
            segTree.update(compressedIndex, 1);
        }

        return count;
    }

    // Segment Tree Implementation
    class SegmentTree {
        int size;
        int[] tree;

        public SegmentTree(int n) {
            size = n;
            tree = new int[4 * n];
        }

        public void update(int index, int val) {
            update(0, size - 1, 1, index, val);
        }

        private void update(int l, int r, int node, int index, int val) {
            if (l == r) {
                tree[node] += val;
                return;
            }
            int mid = (l + r) / 2;
            if (index <= mid) {
                update(l, mid, 2 * node, index, val);
            } else {
                update(mid + 1, r, 2 * node + 1, index, val);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        public int query(int left, int right) {
            return query(0, size - 1, 1, left, right);
        }

        private int query(int l, int r, int node, int left, int right) {
            if (r < left || l > right) {
                return 0;
            }
            if (left <= l && r <= right) {
                return tree[node];
            }
            int mid = (l + r) / 2;
            return query(l, mid, 2 * node, left, right) + query(mid + 1, r, 2 * node + 1, left, right);
        }
    }
}

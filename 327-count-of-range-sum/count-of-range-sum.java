class Solution {
    class SegmentTree {
        int size;
        int[] tree;

        SegmentTree(int size) {
            this.size = size;
            tree = new int[4 * size];
        }

        void update(int index, int l, int r, int pos) {
            if (l == r) {
                tree[pos]++;
                return;
            }
            int mid = (l + r) / 2;
            if (index <= mid)
                update(index, l, mid, 2 * pos);
            else
                update(index, mid + 1, r, 2 * pos + 1);
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
        }

        int query(int ql, int qr, int l, int r, int pos) {
            if (qr < l || r < ql) return 0;
            if (ql <= l && r <= qr) return tree[pos];
            int mid = (l + r) / 2;
            return query(ql, qr, l, mid, 2 * pos) +
                   query(ql, qr, mid + 1, r, 2 * pos + 1);
        }

        void update(int index) {
            update(index, 0, size - 1, 1);
        }

        int query(int left, int right) {
            if (left > right) return 0;
            return query(left, right, 0, size - 1, 1);
        }
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        TreeSet<Long> all = new TreeSet<>();

        // Step 1: compute prefix sum
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 2: collect all values for coordinate compression
        for (long x : prefix) {
            all.add(x);
            all.add(x - lower);
            all.add(x - upper);
        }

        Map<Long, Integer> map = new HashMap<>();
        int id = 0;
        for (long val : all) {
            map.put(val, id++);
        }

        SegmentTree seg = new SegmentTree(id);
        int count = 0;

        for (long sum : prefix) {
            int left = map.get(sum - upper);
            int right = map.get(sum - lower);
            count += seg.query(left, right);
            seg.update(map.get(sum));
        }

        return count;
    }
}

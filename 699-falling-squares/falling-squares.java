class Solution {
    class SegmentTree {
        int[] tree, lazy;
        int size;

        SegmentTree(int size) {
            this.size = size;
            tree = new int[size * 4];
            lazy = new int[size * 4];
        }

        void push(int pos, int l, int r) {
            if (lazy[pos] != 0) {
                tree[pos] = Math.max(tree[pos], lazy[pos]);
                if (l != r) {
                    lazy[pos * 2] = Math.max(lazy[pos * 2], lazy[pos]);
                    lazy[pos * 2 + 1] = Math.max(lazy[pos * 2 + 1], lazy[pos]);
                }
                lazy[pos] = 0;
            }
        }

        void update(int ql, int qr, int val, int l, int r, int pos) {
            push(pos, l, r);
            if (qr < l || r < ql) return;
            if (ql <= l && r <= qr) {
                lazy[pos] = val;
                push(pos, l, r);
                return;
            }
            int mid = (l + r) / 2;
            update(ql, qr, val, l, mid, pos * 2);
            update(ql, qr, val, mid + 1, r, pos * 2 + 1);
            tree[pos] = Math.max(tree[pos * 2], tree[pos * 2 + 1]);
        }

        int query(int ql, int qr, int l, int r, int pos) {
            push(pos, l, r);
            if (qr < l || r < ql) return 0;
            if (ql <= l && r <= qr) return tree[pos];
            int mid = (l + r) / 2;
            return Math.max(query(ql, qr, l, mid, pos * 2),
                            query(ql, qr, mid + 1, r, pos * 2 + 1));
        }

        void update(int l, int r, int val) {
            update(l, r, val, 0, size - 1, 1);
        }

        int query(int l, int r) {
            return query(l, r, 0, size - 1, 1);
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {
        TreeSet<Integer> coords = new TreeSet<>();
        for (int[] p : positions) {
            coords.add(p[0]);
            coords.add(p[0] + p[1] - 1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int x : coords) {
            map.put(x, idx++);
        }

        SegmentTree seg = new SegmentTree(idx);
        List<Integer> res = new ArrayList<>();
        int maxHeight = 0;

        for (int[] p : positions) {
            int l = map.get(p[0]);
            int r = map.get(p[0] + p[1] - 1);
            int base = seg.query(l, r);
            seg.update(l, r, base + p[1]);
            maxHeight = Math.max(maxHeight, base + p[1]);
            res.add(maxHeight);
        }

        return res;
    }
}

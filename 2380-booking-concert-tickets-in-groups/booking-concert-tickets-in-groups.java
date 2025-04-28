class BookMyShow {
    private int n;
    private long m;
    private long[] nextPos;      // next free seat index in each row
    private long[] segSum;       // segment tree: sum of freesum
    private long[] segMax;       // segment tree: max of freesum

    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        nextPos = new long[n];
        segSum = new long[4*n];
        segMax = new long[4*n];
        build(1, 0, n - 1);
    }

    private void build(int idx, int l, int r) {
        if (l == r) {
            segSum[idx] = m;
            segMax[idx] = m;
        } else {
            int mid = (l + r) >>> 1;
            build(idx<<1,    l,    mid);
            build(idx<<1|1, mid+1,   r);
            pull(idx);
        }
    }

    private void pull(int idx) {
        segSum[idx] = segSum[idx<<1] + segSum[idx<<1|1];
        segMax[idx] = Math.max(segMax[idx<<1], segMax[idx<<1|1]);
    }

    // point update freesum[row] = m - nextPos[row]
    private void update(int idx, int l, int r, int row) {
        if (l == r) {
            long frees = m - nextPos[row];
            segSum[idx] = frees;
            segMax[idx] = frees;
        } else {
            int mid = (l + r) >>> 1;
            if (row <= mid) update(idx<<1, l, mid, row);
            else          update(idx<<1|1, mid+1, r, row);
            pull(idx);
        }
    }

    // range max query on [ql, qr]
    private long queryMax(int idx, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return segMax[idx];
        int mid = (l + r) >>> 1;
        return Math.max(
            queryMax(idx<<1, l,    mid, ql, qr),
            queryMax(idx<<1|1,mid+1,  r, ql, qr)
        );
    }

    // range sum query on [ql, qr]
    private long querySum(int idx, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return segSum[idx];
        int mid = (l + r) >>> 1;
        return querySum(idx<<1, l,    mid, ql, qr)
             + querySum(idx<<1|1,mid+1,  r, ql, qr);
    }

    // find leftmost row in [ql,qr] with freesum >= k, or -1
    private int findFirst(int idx, int l, int r, int ql, int qr, long k) {
        if (ql > r || qr < l || segMax[idx] < k) return -1;
        if (l == r) return l;
        int mid = (l + r) >>> 1;
        int left = findFirst(idx<<1, l, mid, ql, qr, k);
        if (left != -1) return left;
        return findFirst(idx<<1|1, mid+1, r, ql, qr, k);
    }

    // gather k seats together in one row ≤ maxRow
    public int[] gather(int k, int maxRow) {
        if (queryMax(1, 0, n - 1, 0, maxRow) < k) return new int[0];
        int row = findFirst(1, 0, n - 1, 0, maxRow, k);
        int seat = (int) nextPos[row];
        nextPos[row] += k;
        update(1, 0, n - 1, row);
        return new int[]{ row, seat };
    }

    // scatter k seats anywhere in rows ≤ maxRow
    public boolean scatter(int k, int maxRow) {
        if (querySum(1, 0, n - 1, 0, maxRow) < k) return false;
        int row = 0;
        while (k > 0) {
            row = findFirst(1, 0, n - 1, row, maxRow, 1);
            long frees = m - nextPos[row];
            long use = Math.min(frees, k);
            nextPos[row] += use;
            k -= use;
            update(1, 0, n - 1, row);
            // next time start from same row (it may still have frees>0)
        }
        return true;
    }
}

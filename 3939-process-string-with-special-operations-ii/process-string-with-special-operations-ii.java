class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        // Length tracking
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (c == '*') {
                len[i + 1] = Math.max(0L, len[i] - 1);
            } else if (c == '#') {
                len[i + 1] = len[i] * 2;
            } else { // '%'
                len[i + 1] = len[i];
            }
        }

        if (k < 0 || k >= len[n]) {
            return '.';
        }

        // Walk backwards
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long before = len[i];
            long after = len[i + 1];

            if (c >= 'a' && c <= 'z') {
                if (k == after - 1) {
                    return c;
                }
            } else if (c == '#') {
                if (before > 0) {
                    k %= before;
                }
            } else if (c == '%') {
                k = before - 1 - k;
            }
            // '*' needs no change to k
        }

        return '.';
    }
}
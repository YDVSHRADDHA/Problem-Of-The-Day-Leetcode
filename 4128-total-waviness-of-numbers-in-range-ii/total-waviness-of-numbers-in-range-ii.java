class Solution {
    static class Node {
        long cnt;
        long sum;
        Node(long c, long s) {
            cnt = c;
            sum = s;
        }
    }

    private char[] digits;
    private Node[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        digits = Long.toString(n).toCharArray();
        int len = digits.length;

        memo = new Node[len + 1][3][11][11];

        Node res = dfs(0, 0, 10, 10, true);
        return res.sum;
    }

    // state:
    // 0 = no non-leading digit yet
    // 1 = exactly one digit seen (l1 valid)
    // 2 = at least two digits seen (l2, l1 valid)
    private Node dfs(int pos, int state, int l2, int l1, boolean tight) {
        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight && memo[pos][state][l2][l1] != null) {
            return memo[pos][state][l2][l1];
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {
            boolean ntight = tight && (d == limit);

            Node child;

            if (state == 0) {
                if (d == 0) {
                    child = dfs(pos + 1, 0, 10, 10, ntight);
                } else {
                    child = dfs(pos + 1, 1, 10, d, ntight);
                }

                totalCnt += child.cnt;
                totalSum += child.sum;
            } else if (state == 1) {
                child = dfs(pos + 1, 2, l1, d, ntight);

                totalCnt += child.cnt;
                totalSum += child.sum;
            } else {
                int add = ((l1 > l2 && l1 > d) || (l1 < l2 && l1 < d)) ? 1 : 0;

                child = dfs(pos + 1, 2, l1, d, ntight);

                totalCnt += child.cnt;
                totalSum += child.sum + (long) add * child.cnt;
            }
        }

        Node ans = new Node(totalCnt, totalSum);

        if (!tight) {
            memo[pos][state][l2][l1] = ans;
        }

        return ans;
    }
}
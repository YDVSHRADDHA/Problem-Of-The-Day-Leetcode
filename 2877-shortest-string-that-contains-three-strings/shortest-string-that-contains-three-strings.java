class Solution {
    public String minimumString(String a, String b, String c) {
        String[] s = {a, b, c};
        int[][] perms = {
            {0,1,2},{0,2,1},
            {1,0,2},{1,2,0},
            {2,0,1},{2,1,0}
        };
        String ans = null;
        for (int[] p : perms) {
            String temp = merge(merge(s[p[0]], s[p[1]]), s[p[2]]);
            if (ans == null
             || temp.length() < ans.length()
             || (temp.length() == ans.length() && temp.compareTo(ans) < 0)) {
                ans = temp;
            }
        }
        return ans;
    }

    private String merge(String s, String t) {
        if (s.contains(t)) return s;
        if (t.contains(s)) return t;
        int m = s.length(), n = t.length();
        for (int i = Math.min(m, n); i > 0; --i) {
            if (s.substring(m - i).equals(t.substring(0, i))) {
                return s + t.substring(i);
            }
        }
        return s + t;
    }
}

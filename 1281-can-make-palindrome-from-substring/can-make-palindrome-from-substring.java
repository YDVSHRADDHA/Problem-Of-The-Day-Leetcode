class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
     
    int n = s.length();
    int[][] prefix = new int[n + 1][26];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 26; j++) {
            prefix[i + 1][j] = prefix[i][j];
        }
        prefix[i + 1][s.charAt(i) - 'a']++;
    }

    List<Boolean> res = new ArrayList<>();
    for (int[] q : queries) {
        int left = q[0], right = q[1], k = q[2];
        int oddCount = 0;
        for (int c = 0; c < 26; c++) {
            int count = prefix[right + 1][c] - prefix[left][c];
            if (count % 2 != 0) oddCount++;
        }
        res.add(oddCount / 2 <= k);
    }
    return res;
  
    }
}
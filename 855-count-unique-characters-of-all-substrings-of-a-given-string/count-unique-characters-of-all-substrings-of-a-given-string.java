class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[] prev = new int[128]; // for ASCII A-Z
        int[] next = new int[128];
        Arrays.fill(prev, -1);
        Arrays.fill(next, n);
        
        int[] lastPos = new int[128];
        Arrays.fill(lastPos, -1);
        
        int[] firstPos = new int[n];
        
        // first pass: record previous index
        for (int i = 0; i < n; i++) {
            firstPos[i] = lastPos[s.charAt(i)];
            lastPos[s.charAt(i)] = i;
        }
        
        // reset for next pass
        Arrays.fill(lastPos, n);
        
        int[] nextPos = new int[n];
        
        // second pass: record next index
        for (int i = n - 1; i >= 0; i--) {
            nextPos[i] = lastPos[s.charAt(i)];
            lastPos[s.charAt(i)] = i;
        }
        
        long res = 0;
        int mod = 1_000_000_007; // (good habit)
        
        for (int i = 0; i < n; i++) {
            res += (long)(i - firstPos[i]) * (nextPos[i] - i);
        }
        
        return (int) res;
    }
}

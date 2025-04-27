class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int[] parent = new int[n];
        
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    union(i, j, parent);
                }
            }
        }
        
        // Count unique parents
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) groups++;
        }
        
        return groups;
    }
    
    private boolean isSimilar(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
            if (diff > 2) return false;
        }
        return diff == 2 || diff == 0;
    }
    
    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
    
    private void union(int x, int y, int[] parent) {
        int px = find(x, parent);
        int py = find(y, parent);
        if (px != py) {
            parent[px] = py;
        }
    }
}

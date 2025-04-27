import java.util.*;

class Solution {
    public int[] groupStrings(String[] words) {
        int n = words.length;
        int[] parent = new int[n];
        int[] size = new int[n];
        
        // Initialize DSU
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        Map<Integer, Integer> maskToIndex = new HashMap<>();
        
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            masks[i] = mask;
            
            if (maskToIndex.containsKey(mask)) {
                union(i, maskToIndex.get(mask), parent, size);
            } else {
                maskToIndex.put(mask, i);
            }
        }
        
        // Now, try deleting or changing one letter
        for (int i = 0; i < n; i++) {
            int mask = masks[i];
            for (int j = 0; j < 26; j++) {
                int bit = (1 << j);
                if ((mask & bit) != 0) {
                    // Try deleting this letter
                    int newMask = mask ^ bit;
                    if (maskToIndex.containsKey(newMask)) {
                        union(i, maskToIndex.get(newMask), parent, size);
                    }
                    
                    // Try replacing this letter with any other letter
                    for (int k = 0; k < 26; k++) {
                        if (k == j) continue;
                        int replaceMask = newMask | (1 << k);
                        if (maskToIndex.containsKey(replaceMask)) {
                            union(i, maskToIndex.get(replaceMask), parent, size);
                        }
                    }
                }
            }
        }
        
        // Count groups and find max group size
        Map<Integer, Integer> groupCount = new HashMap<>();
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            int p = find(i, parent);
            groupCount.put(p, groupCount.getOrDefault(p, 0) + 1);
            maxSize = Math.max(maxSize, groupCount.get(p));
        }
        
        int numGroups = groupCount.size();
        
        return new int[]{numGroups, maxSize};
    }
    
    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
    
    private void union(int x, int y, int[] parent, int[] size) {
        int px = find(x, parent);
        int py = find(y, parent);
        
        if (px == py) return;
        
        if (size[px] < size[py]) {
            parent[px] = py;
            size[py] += size[px];
        } else {
            parent[py] = px;
            size[px] += size[py];
        }
    }
}

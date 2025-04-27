import java.util.*;

class Solution {
    public int largestComponentSize(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        UnionFind uf = new UnionFind(max + 1);
        
        for (int num : nums) {
            for (int i = 2; i * i <= num; ++i) {
                if (num % i == 0) {
                    uf.union(num, i);
                    uf.union(num, num / i);
                }
            }
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int root = uf.find(num);
            count.put(root, count.getOrDefault(root, 0) + 1);
            res = Math.max(res, count.get(root));
        }
        return res;
    }
    
    class UnionFind {
        int[] parent;
        
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; ++i) parent[i] = i;
        }
        
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        
        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}

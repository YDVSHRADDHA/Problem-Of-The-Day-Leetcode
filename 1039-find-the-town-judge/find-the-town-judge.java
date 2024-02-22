import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findJudge(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1;
        }
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();

        for (int[] relationship : trust) {
            outDegree.put(relationship[0], outDegree.getOrDefault(relationship[0], 0) + 1);
            inDegree.put(relationship[1], inDegree.getOrDefault(relationship[1], 0) + 1);
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree.getOrDefault(i, 0) == n - 1 && !outDegree.containsKey(i)) {
                return i;
            }
        }

        return -1;

    }
}
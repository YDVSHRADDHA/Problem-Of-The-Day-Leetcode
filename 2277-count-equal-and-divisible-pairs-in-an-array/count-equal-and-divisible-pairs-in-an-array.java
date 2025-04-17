import java.util.*;

class Solution {
    public int countPairs(int[] nums, int k) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int count = 0;

        // Group indices by value
        for (int i = 0; i < nums.length; i++) {
            indexMap.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        // For each group, check valid pairs (i, j)
        for (List<Integer> indices : indexMap.values()) {
            int size = indices.size();
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    int index1 = indices.get(i);
                    int index2 = indices.get(j);
                    if ((index1 * index2) % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}

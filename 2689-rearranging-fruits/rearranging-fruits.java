import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = basket1.length;

        for (int i = 0; i < n; i++) {
            freq.put(basket1[i], freq.getOrDefault(basket1[i], 0) + 1);
            freq.put(basket2[i], freq.getOrDefault(basket2[i], 0) - 1);
        }

        List<Integer> A_extra = new ArrayList<>();
        List<Integer> B_extra = new ArrayList<>();

        int minFruit = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int fruit = entry.getKey(), count = entry.getValue();
            minFruit = Math.min(minFruit, fruit);
            // If odd difference, impossible
            if (count % 2 != 0) return -1;
            int moves = Math.abs(count) / 2;

            // if basket1 has more
            if (count > 0) {
                for (int i = 0; i < moves; i++) {
                    A_extra.add(fruit);
                }
            } else {
                for (int i = 0; i < moves; i++) {
                    B_extra.add(fruit);
                }
            }
        }

        Collections.sort(A_extra);
        Collections.sort(B_extra, Collections.reverseOrder()); // swap high with low

        long cost = 0;
        for (int i = 0; i < A_extra.size(); i++) {
            int a = A_extra.get(i), b = B_extra.get(i);
            cost += Math.min(Math.min(a, b), 2 * minFruit); // greedy choice
        }

        return cost;
    }
}

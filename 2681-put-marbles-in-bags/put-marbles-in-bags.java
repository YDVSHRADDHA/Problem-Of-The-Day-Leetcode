class Solution {
    public long putMarbles(int[] weights, int k) {

          if (k == 1) return 0; // Only one bag, so min and max scores are equal.

        int n = weights.length;
        List<Integer> pairSums = new ArrayList<>();

        // Compute pair sums
        for (int i = 0; i < n - 1; i++) {
            pairSums.add(weights[i] + weights[i + 1]);
        }

        // Sort the pair sums
        Collections.sort(pairSums);

        long minSum = 0, maxSum = 0;
        int size = pairSums.size();

        // Sum of k-1 smallest elements (min score) and k-1 largest elements (max score)
        for (int i = 0; i < k - 1; i++) {
            minSum += pairSums.get(i);
            maxSum += pairSums.get(size - 1 - i);
        }

        return maxSum - minSum;
    
    }
}
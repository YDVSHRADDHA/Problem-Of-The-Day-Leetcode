public class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int total = 0;

        // Step 1: Calculate total sum of all cards
        for (int point : cardPoints) {
            total += point;
        }

        // Step 2: Sliding window to find min sum subarray of size (n - k)
        int windowSize = n - k;
        int windowSum = 0;
        int minWindowSum = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            windowSum += cardPoints[i];

            if (i >= windowSize) {
                windowSum -= cardPoints[i - windowSize];
            }

            if (i >= windowSize - 1) {
                minWindowSum = Math.min(minWindowSum, windowSum);
            }
        }

        // Step 3: Total - min bad window = max good score
        return total - minWindowSum;
    }
}

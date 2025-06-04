class Solution {
    private int[] prefixSums;
    private int totalSum;

    public Solution(int[] w) {
        prefixSums = new int[w.length];
        prefixSums[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + w[i];
        }
        totalSum = prefixSums[prefixSums.length - 1];
    }
    
    public int pickIndex() {
        double target = totalSum * Math.random();
        int low = 0, high = prefixSums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target >= prefixSums[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}

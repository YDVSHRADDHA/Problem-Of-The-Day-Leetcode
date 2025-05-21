class Solution {
    public int maximumGap(int[] nums) {
  
    if (nums.length < 2) return 0;

    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int num : nums) {
        min = Math.min(min, num);
        max = Math.max(max, num);
    }

    int n = nums.length;
    int bucketSize = Math.max(1, (max - min) / (n - 1));
    int bucketCount = (max - min) / bucketSize + 1;

    int[] bucketMin = new int[bucketCount];
    int[] bucketMax = new int[bucketCount];
    Arrays.fill(bucketMin, Integer.MAX_VALUE);
    Arrays.fill(bucketMax, Integer.MIN_VALUE);

    for (int num : nums) {
        int i = (num - min) / bucketSize;
        bucketMin[i] = Math.min(bucketMin[i], num);
        bucketMax[i] = Math.max(bucketMax[i], num);
    }

    int maxGap = 0, prev = min;
    for (int i = 0; i < bucketCount; i++) {
        if (bucketMin[i] == Integer.MAX_VALUE) continue;
        maxGap = Math.max(maxGap, bucketMin[i] - prev);
        prev = bucketMax[i];
    }
    return maxGap;
}
}
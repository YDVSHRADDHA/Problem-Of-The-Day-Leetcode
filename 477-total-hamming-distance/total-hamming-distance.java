public class Solution {
    public int totalHammingDistance(int[] nums) {
        int total = 0;
        int n = nums.length;

        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int num : nums) {
                // count how many numbers have the i-th bit set
                if ((num & (1 << i)) != 0) {
                    bitCount++;
                }
            }
            // total pairs = bitCount * (n - bitCount)
            total += bitCount * (n - bitCount);
        }

        return total;
    }
}

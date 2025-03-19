public class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int flipCount = 0, ops = 0;
        int[] flipRecord = new int[n + 1];  // Track flip effects
        
        for (int i = 0; i < n; i++) {
            flipCount ^= flipRecord[i];  // Apply pending flips
            
            if ((nums[i] ^ flipCount) == 0) {  // Effective value is 0
                if (i + 2 >= n) return -1;  // If flipping is impossible
                
                // Flip range [i, i+2]
                flipCount ^= 1;
                flipRecord[i + 3] ^= 1;  // Undo effect at i+3
                ops++;
            }
        }
        return ops;
    }
}

class Solution {
    public int minKBitFlips(int[] nums, int k) {
    int n = nums.length;
    int flips = 0; // Total number of flips performed
    int flip = 0;  // Current flip status (0 = no flip, 1 = flipped)
    
    // Array to keep track of where we flipped
    int[] isFlipped = new int[n]; // isFlipped[i] = 1 means we started a flip at index i

    for (int i = 0; i < n; i++) {
        // If we're outside the window size k, remove the effect of the flip that ended
        if (i >= k) {
            flip ^= isFlipped[i - k]; // remove the effect of flip done at index i - k
        }

        // If current bit after applying previous flips is 0, we need to flip
        if ((nums[i] ^ flip) == 0) {
            // Not enough space left to flip k bits from this index
            if (i + k > n) return -1;

            // Mark a flip starting at index i
            isFlipped[i] = 1;
            flip ^= 1; // Flip status toggles
            flips++;   // Count this flip
        }
    }

    return flips; // Return total flips made
}

}
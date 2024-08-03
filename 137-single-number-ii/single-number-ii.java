class Solution {
    public int singleNumber(int[] nums) {
        
       int[] bitCount = new int[32]; // Array to store the count of each bit position
        
        // Count bits for all numbers
      
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                // Check if the i-th bit is set (1) in the current number
                if ((num >> i & 1) == 1) {
                    bitCount[i]++;
                }
            }
        }

          // Reconstruct the single number
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // If the count of 1s in the i-th bit position is not a multiple of 3,
            // it means the single number has a 1 in this bit position
            if (bitCount[i] % 3 != 0) {
                result |= (1 << i); // Set the i-th bit in the result
            }
        }
        
        return result;
    }
      
    }
 
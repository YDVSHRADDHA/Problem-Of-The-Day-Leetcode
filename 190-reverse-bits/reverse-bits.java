public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;        // shift result left to make space
            result |= (n & 1);   // add the least significant bit of n
            n >>>= 1;            // unsigned right shift n by 1 bit
        }
        return result;
    }
}

class Solution {
    public int smallestRepunitDivByK(int k) {
    // We need to check remainders to find if we can reach a 0
    int remainder = 1;
    for (int i = 1; i <= k; i++) {
        if (remainder % k == 0) {
            return i; // If the remainder is divisible by k, return the length of the number formed
        }
        remainder = remainder * 10 + 1; // simulate adding a '1' at the end
        remainder %= k; // take modulus with k to avoid overflow
    }
    return -1; // If no such number exists, return -1
}

}
class Solution {
    public int duplicateNumbersXOR(int[] nums) {
         HashSet<Integer> set = new HashSet<>();
        int xor = 0;

        for (int num : nums) {
            if (set.contains(num)) {
                xor ^= num; // XOR the duplicate number
            } else {
                set.add(num); // Add number to the set
            }
        }

        return xor; // Return the XOR value of all duplicates
    }
}
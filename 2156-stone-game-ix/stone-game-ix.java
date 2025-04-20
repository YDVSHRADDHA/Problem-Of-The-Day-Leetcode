class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Count of stones based on their value mod 3 (i.e., remainder when divided by 3)
        int[] count = new int[3];
        
        // Count how many stones have remainder 0, 1, or 2 when divided by 3
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // If there are only stones divisible by 3 (remainder 0), no valid game can be played
        if (count[1] == 0 && count[2] == 0) {
            return false;
        }

        // If number of mod 0 stones is even, we can ignore them
        // In that case, Alice can win only if both type 1 and type 2 stones exist
        if (count[0] % 2 == 0) {
            return count[1] >= 1 && count[2] >= 1;
        } else {
            // If number of mod 0 stones is odd, it flips the advantage
            // Alice can win if there's a big enough difference between mod 1 and mod 2
            return Math.abs(count[1] - count[2]) > 2;
        }
    }
}

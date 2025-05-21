class Solution {
     
    private Boolean[] memo;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;

        memo = new Boolean[1 << maxChoosableInteger];
        return canWin(0, desiredTotal, maxChoosableInteger);
    }

    private boolean canWin(int used, int desiredTotal, int maxChoosableInteger) {
        if (memo[used] != null) return memo[used];

        for (int i = 0; i < maxChoosableInteger; i++) {
            int cur = 1 << i;
            if ((used & cur) == 0) { // If number i+1 is not used
                // If choosing (i+1) wins immediately, or the opponent loses next turn
                if (i + 1 >= desiredTotal || !canWin(used | cur, desiredTotal - (i + 1), maxChoosableInteger)) {
                    return memo[used] = true;
                }
            }
        }

        return memo[used] = false;
    }
}

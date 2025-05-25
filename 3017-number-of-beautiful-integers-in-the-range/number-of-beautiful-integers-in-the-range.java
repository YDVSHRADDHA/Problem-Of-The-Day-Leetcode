import java.util.Arrays;

public class Solution {
    private int k;
    private char[] digits;
    // Memo: pos, even-odd balance offset (to avoid negatives), mod, tight, leadingZero
    private Integer[][][][][] memo;

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        return countBeautiful(high) - countBeautiful(low - 1);
    }

    private int countBeautiful(int num) {
        if (num < 0) return 0;
        digits = Integer.toString(num).toCharArray();
        // even-odd balance can be from -digits.length to +digits.length, offset by digits.length
        int offset = digits.length;
        memo = new Integer[digits.length][2 * offset + 1][k][2][2];
        return dfs(0, offset, 0, 1, 1);
    }

    private int dfs(int pos, int balance, int mod, int tight, int leadingZero) {
        if (pos == digits.length) {
            // balance == offset means even digits == odd digits
            return (balance == digits.length && mod == 0 && leadingZero == 0) ? 1 : 0;
        }

        if (memo[pos][balance][mod][tight][leadingZero] != null) {
            return memo[pos][balance][mod][tight][leadingZero];
        }

        int limit = tight == 1 ? digits[pos] - '0' : 9;
        int result = 0;

        for (int d = 0; d <= limit; d++) {
            int newTight = (tight == 1 && d == limit) ? 1 : 0;
            int newLeadingZero = (leadingZero == 1 && d == 0) ? 1 : 0;

            // Update balance: +1 for even digit, -1 for odd digit
            // But if leadingZero == 1 and d == 0, we do NOT count digits yet, so balance resets to offset
            int newBalance = balance;
            if (newLeadingZero == 1) {
                // Still leading zeros, balance resets to offset (means 0 difference)
                newBalance = digits.length;
            } else {
                if (d % 2 == 0) newBalance++;
                else newBalance--;
            }

            // If newBalance is out of bounds, skip (should never happen if offset is correct)
            if (newBalance < 0 || newBalance > 2 * digits.length) continue;

            int newMod = (mod * 10 + d) % k;

            result += dfs(pos + 1, newBalance, newMod, newTight, newLeadingZero);
        }

        memo[pos][balance][mod][tight][leadingZero] = result;
        return result;
    }
}

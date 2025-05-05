class Solution {
    private int[] matchsticks;
    private int sideLength;
    private Map<String, Boolean> memo;

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) sum += stick;
        if (sum % 4 != 0) return false;

        this.matchsticks = matchsticks;
        this.sideLength = sum / 4;
        this.memo = new HashMap<>();
        Arrays.sort(matchsticks); // sort for better pruning
        reverse(matchsticks);

        return dfs(0, 0, 0);
    }

    private boolean dfs(int mask, int currSum, int sidesDone) {
        if (sidesDone == 3) return true; // last side is implicitly filled
        String key = mask + "," + currSum + "," + sidesDone;
        if (memo.containsKey(key)) return memo.get(key);

        for (int i = 0; i < matchsticks.length; i++) {
            if ((mask & (1 << i)) != 0) continue; // already used

            int nextSum = currSum + matchsticks[i];
            if (nextSum > sideLength) continue;

            int newMask = mask | (1 << i);

            if (nextSum == sideLength) {
                if (dfs(newMask, 0, sidesDone + 1)) {
                    memo.put(key, true);
                    return true;
                }
            } else {
                if (dfs(newMask, nextSum, sidesDone)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }

        memo.put(key, false);
        return false;
    }

    private void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}

















// class Solution {
//     public boolean makesquare(int[] matchsticks) {
//         int sum = 0;
//         for (int stick : matchsticks) sum += stick;
//         if (sum % 4 != 0) return false;
//         int side = sum / 4;
//         Arrays.sort(matchsticks); // optional but helps prune faster
//         reverse(matchsticks); // Try bigger sticks first for quicker pruning
//         int[] sides = new int[4];
//         return dfs(matchsticks, 0, sides, side);
//     }

//     private boolean dfs(int[] matchsticks, int index, int[] sides, int target) {
//         if (index == matchsticks.length)
//             return sides[0] == target && sides[1] == target &&
//                    sides[2] == target && sides[3] == target;

//         for (int i = 0; i < 4; i++) {
//             if (sides[i] + matchsticks[index] > target) continue;
//             sides[i] += matchsticks[index];
//             if (dfs(matchsticks, index + 1, sides, target)) return true;
//             sides[i] -= matchsticks[index];
//         }

//         return false;
//     }

//     private void reverse(int[] arr) {
//         int i = 0, j = arr.length - 1;
//         while (i < j) {
//             int tmp = arr[i];
//             arr[i] = arr[j];
//             arr[j] = tmp;
//             i++; j--;
//         }
//     }
// }

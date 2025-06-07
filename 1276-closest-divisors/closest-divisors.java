class Solution {
    public int[] closestDivisors(int num) {
        int[] res1 = getClosestPair(num + 1);
        int[] res2 = getClosestPair(num + 2);
        
        int diff1 = Math.abs(res1[0] - res1[1]);
        int diff2 = Math.abs(res2[0] - res2[1]);

        return diff1 <= diff2 ? res1 : res2;
    }

    private int[] getClosestPair(int target) {
        int sqrt = (int)Math.sqrt(target);
        for (int i = sqrt; i >= 1; i--) {
            if (target % i == 0) {
                return new int[]{i, target / i};
            }
        }
        return new int[]{1, target}; // fallback
    }
}

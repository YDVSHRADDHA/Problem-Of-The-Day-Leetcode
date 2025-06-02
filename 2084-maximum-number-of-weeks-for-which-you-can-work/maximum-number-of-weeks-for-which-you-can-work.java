class Solution {
    public long numberOfWeeks(int[] milestones) {
        long total = 0;
        int max = 0;

        for (int m : milestones) {
            total += m;
            max = Math.max(max, m);
        }

        if (max <= total - max) {
            return total;
        } else {
            return 2 * (total - max) + 1;
        }
    }
}

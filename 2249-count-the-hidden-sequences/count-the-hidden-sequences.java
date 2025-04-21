public class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long prefix = 0;
        long max = 0, min = 0;
        
        for (int diff : differences) {
            prefix += diff;
            max = Math.max(max, prefix);
            min = Math.min(min, prefix);
        }

        long minX = lower - min;
        long maxX = upper - max;

        long count = maxX - minX + 1;
        return (int) Math.max(0, count);
    }
}

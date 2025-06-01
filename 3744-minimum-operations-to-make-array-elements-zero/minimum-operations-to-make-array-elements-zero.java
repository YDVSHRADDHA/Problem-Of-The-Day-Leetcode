class Solution {
    private static final long[] POWERS = new long[21];
    static {
        POWERS[0] = 1L;
        for (int i = 1; i < 21; i++) {
            POWERS[i] = POWERS[i - 1] * 4L;
        }
    }
    
    private long sumLog4(long l, long r) {
        long total = 0;
        for (int k = 0; k < POWERS.length - 1; k++) {
            long start = POWERS[k];
            long end = POWERS[k + 1] - 1;
            
            if (r < start) break;
            
            long left = Math.max(l, start);
            long right = Math.min(r, end);
            
            if (left <= right) {
                total += k * (right - left + 1);
            }
        }
        return total;
    }
    
    public long minOperations(int[][] queries) {
        long totalOperations = 0;
        
        for (int[] query : queries) {
            long l = query[0];
            long r = query[1];
            long length = r - l + 1;
            long sumLog = sumLog4(l, r);
            
            long totalDivisions = length + sumLog;
            // Use ceiling division by 2
            long ops = (totalDivisions + 1) / 2;
            totalOperations += ops;
        }
        
        return totalOperations;
    }
}

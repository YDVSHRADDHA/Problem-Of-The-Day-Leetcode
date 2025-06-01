class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;
        int n = corridor.length();
        
        // Count total seats
        int totalSeats = 0;
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S') totalSeats++;
        }
        
        // If totalSeats is 0 or odd, no valid division
        if (totalSeats == 0 || totalSeats % 2 != 0) return 0;
        
        int ways = 1;
        int seatsCounted = 0;
        int i = 0;
        
        // We'll find the segments between pairs of seats
        while (i < n && seatsCounted < totalSeats) {
            // Move to the (seatsCounted + 1)-th seat
            while (i < n && corridor.charAt(i) != 'S') i++;
            seatsCounted++;
            i++;
            
            // After every even seat, calculate plants between this and next seat
            if (seatsCounted % 2 == 0 && seatsCounted < totalSeats) {
                int plantsCount = 0;
                // Count plants until next seat
                while (i < n && corridor.charAt(i) != 'S') {
                    plantsCount++;
                    i++;
                }
                // Multiply ways by number of ways to place divider = plantsCount + 1
                ways = (int)((long)ways * (plantsCount + 1) % MOD);
            }
        }
        
        return ways;
    }
}

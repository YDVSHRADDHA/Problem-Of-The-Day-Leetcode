class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
 
        int[] diff = new int[n + 1];  // difference array

        // Apply all bookings in O(m)
        for (int[] booking : bookings) {
            int start = booking[0] - 1; // zero-based index
            int end = booking[1];       // note: end is used for subtracting at end+1
            int seats = booking[2];

            diff[start] += seats;
            if (end < n) {
                diff[end] -= seats;
            }
        }

        // Prefix sum to get final seats reserved for each flight
        int[] answer = new int[n];
        int current = 0;
        for (int i = 0; i < n; i++) {
            current += diff[i];
            answer[i] = current;
        }

        return answer;
    }
}

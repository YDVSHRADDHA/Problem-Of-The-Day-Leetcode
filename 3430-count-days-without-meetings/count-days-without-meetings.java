import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        if (meetings.length == 0) return days;

        // Step 1: Sort meetings based on start day
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Merge overlapping meetings
        int totalMeetingDays = 0;
        int start = meetings[0][0], end = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            int[] curr = meetings[i];
            if (curr[0] <= end + 1) {
                // Overlapping interval, extend the end
                end = Math.max(end, curr[1]);
            } else {
                // Non-overlapping, count days & start new interval
                totalMeetingDays += (end - start + 1);
                start = curr[0];
                end = curr[1];
            }
        }
        // Add last interval count
        totalMeetingDays += (end - start + 1);

        // Step 3: Compute available days
        return days - totalMeetingDays;
    }
}

class Solution {
    public int videoStitching(int[][] clips, int time) {
 
    Arrays.sort(clips, (a, b) -> a[0] - b[0]);

    int count = 0, end = 0, i = 0, n = clips.length;
    
    while (end < time) {
        int farthest = end;
        // Extend coverage as far as we can within current range
        while (i < n && clips[i][0] <= end) {
            farthest = Math.max(farthest, clips[i][1]);
            i++;
        }
        // If we can’t extend any further, coverage is incomplete
        if (farthest == end) return -1;

        count++;
        end = farthest;
    }

    return count;
}

}
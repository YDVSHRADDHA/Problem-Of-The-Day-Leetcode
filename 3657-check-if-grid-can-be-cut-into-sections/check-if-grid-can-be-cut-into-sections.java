import java.util.*;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        return canCut(rectangles, true) || canCut(rectangles, false);
    }

    // Try cutting vertically or horizontally based on flag
    private boolean canCut(int[][] rects, boolean isVertical) {
        // Sort based on startx (if vertical) or starty (if horizontal)
        Arrays.sort(rects, (a, b) -> {
            return Integer.compare(isVertical ? a[0] : a[1], isVertical ? b[0] : b[1]);
        });

        List<int[]> groups = new ArrayList<>();
        int start = isVertical ? rects[0][0] : rects[0][1];
        int end = isVertical ? rects[0][2] : rects[0][3];

        for (int i = 1; i < rects.length; i++) {
            int currStart = isVertical ? rects[i][0] : rects[i][1];
            int currEnd = isVertical ? rects[i][2] : rects[i][3];

            if (currStart >= end) {
                // Current rectangle starts after previous ends → new group
                groups.add(new int[]{start, end});
                start = currStart;
                end = currEnd;
            } else {
                // Overlaps → extend current group
                end = Math.max(end, currEnd);
            }
        }
        groups.add(new int[]{start, end});

        // We need at least 3 disjoint groups for two cuts to work
        return groups.size() >= 3;
    }
}

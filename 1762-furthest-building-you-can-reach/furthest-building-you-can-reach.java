class Solution {
       public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Priority queue (min heap) to store the largest height differences
        PriorityQueue<Integer> ladderQueue = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];

            if (diff > 0) { // If the next building is taller
                ladderQueue.offer(diff); // Store the height difference in the priority queue

                // If the number of stored differences exceeds the available ladders
                if (ladderQueue.size() > ladders) {
                    bricks -= ladderQueue.poll(); // Use bricks for the largest differences
                }

                // If bricks are not sufficient to cover the differences, return the current building index
                if (bricks < 0) {
                    return i;
                }
            }
        }

        // If the loop completes, it means all buildings are reachable, so return the index of the last building
        return heights.length - 1;
    }
//  don't worry! <<< hmme khud k logic cchiye! Shraddha! priority queue!
}
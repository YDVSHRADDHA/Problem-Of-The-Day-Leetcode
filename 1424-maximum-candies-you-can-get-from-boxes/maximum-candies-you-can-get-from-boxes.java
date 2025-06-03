
class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasKey = new boolean[n];
        boolean[] owned = new boolean[n];
        boolean[] used = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        
        // Add all initial boxes to owned
        for (int box : initialBoxes) {
            owned[box] = true;
            if (status[box] == 1) {
                queue.offer(box);
            }
        }
        
        int totalCandies = 0;
        
        while (!queue.isEmpty()) {
            int box = queue.poll();
            if (used[box]) continue;
            used[box] = true;
            
            // Collect candies
            totalCandies += candies[box];
            
            // Use the keys in this box
            for (int key : keys[box]) {
                hasKey[key] = true;
                // If we own the box and haven't used it and it was previously locked
                if (owned[key] && !used[key] && status[key] == 0) {
                    status[key] = 1;
                    queue.offer(key);
                }
            }
            
            // Collect the boxes inside
            for (int contained : containedBoxes[box]) {
                owned[contained] = true;
                if ((status[contained] == 1 || hasKey[contained]) && !used[contained]) {
                    queue.offer(contained);
                }
            }
        }
        
        return totalCandies;
    }
}

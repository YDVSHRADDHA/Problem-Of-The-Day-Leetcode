import java.util.*;

class Solution {
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int i = queue.poll();
            
            // If we found a zero, return true
            if (arr[i] == 0) return true;

            // Skip if already visited
            if (arr[i] < 0) continue;

            // Possible next moves
            int forward = i + arr[i];
            int backward = i - arr[i];

            // Mark visited
            arr[i] = -1;

            // Add next moves if within bounds
            if (forward < arr.length) queue.add(forward);
            if (backward >= 0) queue.add(backward);
        }

        return false;
    }
}

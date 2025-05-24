import java.util.*;

class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = new HashSet<>();
        for (int pos : forbidden) forbiddenSet.add(pos);

        // Visited array: visited[position][0 or 1]
        // 0 = last jump was forward or start, 1 = last jump was backward
        boolean[][] visited = new boolean[6001][2];
        
        Queue<int[]> queue = new LinkedList<>();
        // state: [position, lastJumpWasBackward (0 or 1), steps]
        queue.offer(new int[]{0, 0, 0});
        visited[0][0] = true;

        int upperBound = Math.max(x, Arrays.stream(forbidden).max().getAsInt()) + a + b;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0], lastBack = curr[1], steps = curr[2];
            if (pos == x) return steps;

            // Jump forward
            int forward = pos + a;
            if (forward <= upperBound && !forbiddenSet.contains(forward) && !visited[forward][0]) {
                visited[forward][0] = true;
                queue.offer(new int[]{forward, 0, steps + 1});
            }

            // Jump backward only if last jump was not backward
            int backward = pos - b;
            if (lastBack == 0 && backward >= 0 && !forbiddenSet.contains(backward) && !visited[backward][1]) {
                visited[backward][1] = true;
                queue.offer(new int[]{backward, 1, steps + 1});
            }
        }

        return -1;
    }
}

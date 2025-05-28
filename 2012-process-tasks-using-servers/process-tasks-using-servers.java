import java.util.*;

public class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: [weight, index]
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
        );
        
        // Min-heap for busy servers: [freeTime, weight, index]
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : 
                     (a[1] != b[1] ? a[1] - b[1] : a[2] - b[2])
        );

        // Initially, all servers are free
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int time = 0;
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            // Move any busy server that is now free to the free heap
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                free.offer(new int[]{server[1], server[2]});
            }

            // If no servers are free, advance time to when the next server is free
            if (free.isEmpty()) {
                time = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] server = busy.poll();
                    free.offer(new int[]{server[1], server[2]});
                }
            }

            // Assign task to the best available server
            int[] server = free.poll();
            ans[i] = server[1];
            busy.offer(new int[]{time + tasks[i], server[0], server[1]});
        }

        return ans;
    }
}

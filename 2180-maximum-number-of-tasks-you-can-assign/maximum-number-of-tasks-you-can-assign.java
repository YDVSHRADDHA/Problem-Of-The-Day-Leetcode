class Solution {

    // Main function to find the maximum number of tasks that can be assigned
    public int maxTaskAssign(
        int[] tasks,      // Array of task strength requirements
        int[] workers,    // Array of worker strengths
        int pills,        // Number of magical pills available
        int strength      // Strength each pill adds
    ) {
        int n = tasks.length, m = workers.length;

        // Sort tasks and workers to apply greedy strategy
        Arrays.sort(tasks);
        Arrays.sort(workers);

        // Binary search boundaries: from 1 task to min(n, m) tasks
        int left = 1, right = Math.min(m, n), ans = 0;

        // Binary search to find the maximum number of tasks that can be assigned
        while (left <= right) {
            int mid = (left + right) / 2; // Try assigning 'mid' number of tasks

            // Check if it's possible to assign 'mid' tasks
            if (check(tasks, workers, pills, strength, mid)) {
                ans = mid;         // If yes, store this as a possible answer
                left = mid + 1;    // Try assigning more tasks
            } else {
                right = mid - 1;   // Try assigning fewer tasks
            }
        }
        return ans; // Return the maximum number of assignable tasks
    }

    // Helper function to check if we can assign 'mid' tasks using available pills and strength
    private boolean check(
        int[] tasks,
        int[] workers,
        int pills,
        int strength,
        int mid
    ) {
        int p = pills;                 // Remaining pills
        int m = workers.length;
        Deque<Integer> ws = new ArrayDeque<>(); // Deque to store available workers for current task
        int ptr = m - 1;

        // Iterate over the hardest 'mid' tasks from right to left
        for (int i = mid - 1; i >= 0; --i) {

            // Add eligible workers to deque who can do this task with or without pill
            while (ptr >= m - mid && workers[ptr] + strength >= tasks[i]) {
                ws.addFirst(workers[ptr]);
                --ptr;
            }

            if (ws.isEmpty()) {
                // No worker can do this task even with a pill
                return false;
            } else if (ws.getLast() >= tasks[i]) {
                // Strongest available worker can do the task without a pill
                ws.pollLast();
            } else {
                // Need to use a pill on weakest available worker
                if (p == 0) {
                    // No pills left, can't assign this task
                    return false;
                }
                --p;           // Use one pill
                ws.pollFirst(); // Assign pill to weakest eligible worker
            }
        }

        return true; // All 'mid' tasks assigned successfully
    }
}

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // Combine profits and capital in one array and sort by capital
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];  // capital[i]
            projects[i][1] = profits[i];  // profits[i]
        }

        // Sort projects by their capital requirement
        Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        // Max-heap for selecting the project with maximum profit
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        int idx = 0;

        // Execute at most k projects
        for (int i = 0; i < k; i++) {
            // Add all projects that we can afford to the heap
            while (idx < n && projects[idx][0] <= w) {
                maxHeap.offer(projects[idx][1]);  // Add the profit of this project to the max-heap
                idx++;
            }

            // If we can't afford any more projects, stop
            if (maxHeap.isEmpty()) break;

            // Select the project with the maximum profit
            w += maxHeap.poll();  // Add the profit of the project
        }

        return w;
    }
}

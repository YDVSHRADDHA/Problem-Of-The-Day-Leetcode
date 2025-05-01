class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]); // Sort by deadline
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int currentTime = 0;

        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];

            currentTime += duration;
            maxHeap.offer(duration);

            if (currentTime > lastDay) {
                currentTime -= maxHeap.poll(); // Drop the longest course taken so far
            }
        }

        return maxHeap.size(); // Number of courses taken
    }
}

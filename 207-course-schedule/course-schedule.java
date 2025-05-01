class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

        // Build graph and in-degree array
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Start with courses that have no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int next : graph.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }

        return count == numCourses;
    }
}

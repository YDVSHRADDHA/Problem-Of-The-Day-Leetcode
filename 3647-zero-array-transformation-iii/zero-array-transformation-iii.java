class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        // Sort queries by their starting point (l)
        Arrays.sort(queries, Comparator.comparingInt(q -> q[0]));
        
        // Max-heap to store available queries' end indices (r)
        // These queries start at or before current index i, but are not used yet
        PriorityQueue<Integer> available = new PriorityQueue<>(Collections.reverseOrder());
        
        // Min-heap to store currently running queries' end indices (r)
        // These queries are covering the current index i
        PriorityQueue<Integer> running = new PriorityQueue<>();
        
        int queryIndex = 0;  // To iterate over sorted queries
        
        // Iterate over each index i in nums
        for (int i = 0; i < nums.length; ++i) {
            
            // Add to available all queries starting at or before i
            while (queryIndex < queries.length && queries[queryIndex][0] <= i) {
                available.offer(queries[queryIndex][1]);
                queryIndex++;
            }
            
            // Remove from running all queries that end before i (no longer cover i)
            while (!running.isEmpty() && running.peek() < i) {
                running.poll();
            }
            
            // While current coverage is less than needed at index i
            while (running.size() < nums[i]) {
                // If no available query can cover index i, return -1 (impossible)
                if (available.isEmpty() || available.peek() < i) {
                    return -1;
                }
                
                // Pick the available query with the largest end (r) to cover i
                // Add it to running queries
                running.offer(available.poll());
            }
        }
        
        // At the end, queries left in available are unused and can be removed
        return available.size();
    }
}

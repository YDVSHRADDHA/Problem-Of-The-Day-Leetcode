class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // We use this array to keep track of which rooms we've already visited.
        boolean[] visited = new boolean[rooms.size()];

        // Start our DFS journey from Room 0 (which is already open)
        dfs(0, rooms, visited);

        // After visiting as many rooms as we can, check if any were left out
        for (boolean hasVisited : visited) {
            if (!hasVisited) return false; // If even one room wasn't visited, we return false
        }

        // If all rooms were visited, we return true
        return true;
    }

    // This is our DFS function: it visits a room and explores keys inside it
    private void dfs(int roomNumber, List<List<Integer>> rooms, boolean[] visited) {
        // Mark this room as visited
        visited[roomNumber] = true;

        // Go through all the keys found in this room
        for (int key : rooms.get(roomNumber)) {
            // If the room for this key hasn’t been visited yet, visit it now
            if (!visited[key]) {
                dfs(key, rooms, visited);  // Recursively explore that room
            }
        }
    }
}

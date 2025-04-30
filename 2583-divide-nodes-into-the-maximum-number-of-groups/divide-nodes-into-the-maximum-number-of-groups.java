class Solution {

    // Main function to calculate the maximum number of groups for the entire graph
    public int magnificentSets(int n, int[][] edges) {
        // Initialize an adjacency list to represent the graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Arrays to keep track of the parent of each node and the depth (rank) of the tree in Union-Find
        int[] parent = new int[n];
        int[] depth = new int[n];
        Arrays.fill(parent, -1); // Initialize parent of each node as -1 (indicating no parent)

        // Build the adjacency list and apply Union-Find for each edge
        for (int[] edge : edges) {
            adjList.get(edge[0] - 1).add(edge[1] - 1); // Add edge from node[0] to node[1]
            adjList.get(edge[1] - 1).add(edge[0] - 1); // Add edge from node[1] to node[0]
            union(edge[0] - 1, edge[1] - 1, parent, depth); // Connect the two nodes in Union-Find
        }

        // Map to store the maximum number of groups for each component
        Map<Integer, Integer> numOfGroupsForComponent = new HashMap<>();

        // For each node, calculate the maximum number of groups for its component
        for (int node = 0; node < n; node++) {
            // Get the number of groups (layers) for the current node's component
            int numberOfGroups = getNumberOfGroups(adjList, node, n);
            if (numberOfGroups == -1) return -1; // If invalid split, return -1 (cannot divide into valid groups)
            
            // Find the root of the current node's component using Union-Find
            int rootNode = find(node, parent);
            
            // Update the maximum number of groups for the current root (component)
            numOfGroupsForComponent.put(
                rootNode,
                Math.max(
                    numOfGroupsForComponent.getOrDefault(rootNode, 0),
                    numberOfGroups
                )
            );
        }

        // Calculate the total number of groups across all components
        int totalNumberOfGroups = 0;
        for (int numberOfGroups : numOfGroupsForComponent.values()) {
            totalNumberOfGroups += numberOfGroups;
        }
        
        // Return the total number of groups across all components
        return totalNumberOfGroups;
    }

    // Function to calculate the number of groups (layers) for a given component starting from srcNode
    private int getNumberOfGroups(
        List<List<Integer>> adjList,  // Adjacency list of the graph
        int srcNode,                 // Starting node of the component
        int n                        // Total number of nodes in the graph
    ) {
        // Queue to perform a Breadth-First Search (BFS)
        Queue<Integer> nodesQueue = new LinkedList<>();
        // Array to track which layer (group) each node is in
        int[] layerSeen = new int[n];
        Arrays.fill(layerSeen, -1); // Initially, no nodes are seen (set to -1)
        
        // Start BFS from the source node
        nodesQueue.offer(srcNode);
        layerSeen[srcNode] = 0; // The starting node is at layer 0
        
        int deepestLayer = 0; // Track the deepest layer reached

        // Perform BFS to calculate the number of layers (groups)
        while (!nodesQueue.isEmpty()) {
            int numOfNodesInLayer = nodesQueue.size();
            for (int i = 0; i < numOfNodesInLayer; i++) {
                int currentNode = nodesQueue.poll();
                // Explore all the neighbors of the current node
                for (int neighbor : adjList.get(currentNode)) {
                    // If neighbor hasn't been visited, assign it to the next layer
                    if (layerSeen[neighbor] == -1) {
                        layerSeen[neighbor] = deepestLayer + 1;
                        nodesQueue.offer(neighbor);
                    } else {
                        // If the neighbor is already in the same layer as the current node, return -1 (invalid partition)
                        if (layerSeen[neighbor] == deepestLayer) {
                            return -1;
                        }
                    }
                }
            }
            deepestLayer++; // Move to the next layer after processing the current one
        }

        // Return the deepest layer reached, which represents the number of groups
        return deepestLayer;
    }

    // Find the root of the given node in the Union-Find structure
    private int find(int node, int[] parent) {
        while (parent[node] != -1) {
            node = parent[node]; // Move to the parent of the node until we reach the root
        }
        return node; // Return the root node
    }

    // Union operation to merge two sets (components)
    private void union(int node1, int node2, int[] parent, int[] depth) {
        // Find the roots of the two nodes
        node1 = find(node1, parent);
        node2 = find(node2, parent);

        // If both nodes are already in the same set, do nothing
        if (node1 == node2) return;

        // Union by rank (depth) to keep the tree balanced
        if (depth[node1] < depth[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp; // Ensure node1 is the deeper tree (higher rank)
        }
        
        // Make node2 a child of node1
        parent[node2] = node1;

        // If both nodes have the same rank, increase the rank of the new root node
        if (depth[node1] == depth[node2]) {
            depth[node1]++;
        }
    }
}

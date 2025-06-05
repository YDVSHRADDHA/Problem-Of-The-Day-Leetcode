/**
 * @param {string[][]} equations
 * @param {number[]} values
 * @param {string[][]} queries
 * @return {number[]}
 */
var calcEquation = function(equations, values, queries) {
    // Build graph as adjacency list: node -> { neighbor: weight }
    const graph = new Map();

    // Helper function to add edge
    function addEdge(from, to, val) {
        if (!graph.has(from)) graph.set(from, new Map());
        graph.get(from).set(to, val);
    }

    // Build the graph
    for (let i = 0; i < equations.length; i++) {
        const [A, B] = equations[i];
        const val = values[i];
        addEdge(A, B, val);
        addEdge(B, A, 1 / val);
    }

    // BFS to find the value from start to end
    function bfs(start, end) {
        if (!graph.has(start) || !graph.has(end)) return -1.0;
        if (start === end) return 1.0;

        const visited = new Set();
        const queue = [[start, 1.0]];

        while (queue.length) {
            const [node, product] = queue.shift();
            if (node === end) return product;
            visited.add(node);

            const neighbors = graph.get(node);
            for (const [neighbor, value] of neighbors.entries()) {
                if (!visited.has(neighbor)) {
                    queue.push([neighbor, product * value]);
                }
            }
        }

        return -1.0;
    }

    // Process queries
    const results = [];
    for (const [C, D] of queries) {
        results.push(bfs(C, D));
    }
    return results;
};

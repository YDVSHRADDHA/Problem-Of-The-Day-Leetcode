var minReorder = function(n, connections) {
    const adj = new Map(); // adjacency list
    const edges = new Set(); // to track original direction

    // Build the graph
    for (let [u, v] of connections) {
        if (!adj.has(u)) adj.set(u, []);
        if (!adj.has(v)) adj.set(v, []);
        adj.get(u).push(v);
        adj.get(v).push(u);
        edges.add(`${u},${v}`); // store the original direction
    }

    let changes = 0;

    const dfs = (node, parent) => {
        for (let neighbor of adj.get(node)) {
            if (neighbor === parent) continue;
            if (edges.has(`${node},${neighbor}`)) {
                changes++; // edge going away from 0, needs to be reversed
            }
            dfs(neighbor, node);
        }
    };

    dfs(0, -1); // start from city 0
    return changes;
};

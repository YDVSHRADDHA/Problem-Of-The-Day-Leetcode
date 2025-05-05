var validPath = function(n, edges, source, destination) {
    const adj = Array.from({length: n}, () => []);
    for (let [u, v] of edges) {
        adj[u].push(v);
        adj[v].push(u); // undirected
    }

    const visited = new Array(n).fill(false);
    const queue = [source];
    visited[source] = true;

    while (queue.length) {
        const node = queue.shift();
        if (node === destination) return true;

        for (let neighbor of adj[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.push(neighbor);
            }
        }
    }

    return false;
};

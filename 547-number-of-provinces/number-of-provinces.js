/**
 * @param {number[][]} isConnected
 * @return {number}
 */
var findCircleNum = function(isConnected) {
    
};

var findCircleNum = function(isConnected) {
    const n = isConnected.length;
    const visited = new Array(n).fill(false);
    let provinces = 0;

    const bfs = (start) => {
        const queue = [start];
        visited[start] = true;

        while (queue.length > 0) {
            const node = queue.shift();
            for (let neighbor = 0; neighbor < n; neighbor++) {
                if (isConnected[node][neighbor] === 1 && !visited[neighbor]) {
                    queue.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    };

    for (let i = 0; i < n; i++) {
        if (!visited[i]) {
            provinces++;
            bfs(i);
        }
    }

    return provinces;
};

// ✅ Example usage:
const isConnected = [
  [1, 1, 0],
  [1, 1, 0],
  [0, 0, 1]
];
console.log(findCircleNum(isConnected)); // Output: 2

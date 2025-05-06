var numIslands = function(grid) {
    if (!grid || grid.length === 0) return 0;

    const rows = grid.length;
    const cols = grid[0].length;
    let islandCount = 0;

    const directions = [
        [-1, 0], // up
        [1, 0],  // down
        [0, -1], // left
        [0, 1]   // right
    ];

    const bfs = (r, c) => {
        const queue = [[r, c]];
        grid[r][c] = '0'; // mark as visited

        while (queue.length > 0) {
            const [x, y] = queue.shift();

            for (let [dx, dy] of directions) {
                const newX = x + dx;
                const newY = y + dy;

                if (
                    newX >= 0 && newX < rows &&
                    newY >= 0 && newY < cols &&
                    grid[newX][newY] === '1'
                ) {
                    queue.push([newX, newY]);
                    grid[newX][newY] = '0'; // mark visited
                }
            }
        }
    };

    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            if (grid[r][c] === '1') {
                bfs(r, c);
                islandCount++;
            }
        }
    }

    return islandCount;
};

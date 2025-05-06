/**
 * @param {character[][]} maze
 * @param {number[]} entrance
 * @return {number}
 */
var nearestExit = function(maze, entrance) {
 
    const rows = maze.length;
    const cols = maze[0].length;
    const directions = [[1,0], [-1,0], [0,1], [0,-1]];
    const queue = [[entrance[0], entrance[1], 0]];

    // Mark entrance as visited
    maze[entrance[0]][entrance[1]] = '+';

    while (queue.length > 0) {
        const [r, c, steps] = queue.shift();

        for (let [dr, dc] of directions) {
            const nr = r + dr;
            const nc = c + dc;

            // Check bounds and not a wall
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && maze[nr][nc] === '.') {
                // If it's a border cell and not the entrance
                if (nr === 0 || nr === rows - 1 || nc === 0 || nc === cols - 1) {
                    return steps + 1;
                }
                // Mark as visited and enqueue
                maze[nr][nc] = '+';
                queue.push([nr, nc, steps + 1]);
            }
        }
    }

    return -1; // No exit found
}

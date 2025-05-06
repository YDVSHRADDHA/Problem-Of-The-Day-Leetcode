function orangesRotting(grid) {
  const rows = grid.length, cols = grid[0].length;
  const queue = [];
  let fresh = 0, time = 0;

  for (let r = 0; r < rows; r++) {
    for (let c = 0; c < cols; c++) {
      if (grid[r][c] === 2) queue.push([r, c]);
      if (grid[r][c] === 1) fresh++;
    }
  }

  const directions = [[-1,0],[1,0],[0,-1],[0,1]];

  while (queue.length > 0 && fresh > 0) {
    let size = queue.length;
    for (let i = 0; i < size; i++) {
      const [x, y] = queue.shift();
      for (let [dx, dy] of directions) {
        const nx = x + dx, ny = y + dy;
        if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] === 1) {
          grid[nx][ny] = 2;
          queue.push([nx, ny]);
          fresh--;
        }
      }
    }
    time++;
  }

  return fresh === 0 ? time : -1;
}

/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function(grid) {
    const m = grid.length;
    const n = grid[0].length;

    let row = 0;
    let col = n - 1;
    let count = 0;

    while (row < m && col >= 0) {
        if (grid[row][col] < 0) {
            count += (m - row);
            col--;
        } else {
            row++;
        }
    }

    return count;
};

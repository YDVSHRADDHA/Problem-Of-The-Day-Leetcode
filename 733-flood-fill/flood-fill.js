var floodFill = function(image, sr, sc, color) {
    let original = image[sr][sc];
    if (original === color) return image;

    let queue = [[sr, sc]];
    let directions = [[1,0], [-1,0], [0,1], [0,-1]];

    while (queue.length > 0) {
        let [x, y] = queue.shift();
        image[x][y] = color;

        for (let [dx, dy] of directions) {
            let nx = x + dx, ny = y + dy;
            if (
                nx >= 0 && ny >= 0 && nx < image.length && ny < image[0].length &&
                image[nx][ny] === original
            ) {
                queue.push([nx, ny]);
            }
        }
    }

    return image;
};

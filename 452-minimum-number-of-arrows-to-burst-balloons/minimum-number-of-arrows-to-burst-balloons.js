/**
 * @param {number[][]} points
 * @return {number}
 */
var findMinArrowShots = function(points) {
 
    if (points.length === 0) return 0;

    // Sort balloons by their end coordinate
    points.sort((a, b) => a[1] - b[1]);

    let arrows = 1;
    let arrowPos = points[0][1];

    for (let i = 1; i < points.length; i++) {
        // If current balloon starts after the last arrow position, shoot a new arrow
        if (points[i][0] > arrowPos) {
            arrows++;
            arrowPos = points[i][1];
        }
        // Else current balloon is already burst by existing arrow
    }

    return arrows;
 
};
/**
 * @param {number[]} distance
 * @param {number} start
 * @param {number} destination
 * @return {number}
 */
var distanceBetweenBusStops = function(distance, start, destination) {
    if (start > destination) {
        // Always make start < destination to simplify clockwise sum
        [start, destination] = [destination, start];
    }

    let total = 0;
    let clockwise = 0;

    for (let i = 0; i < distance.length; i++) {
        total += distance[i];
        if (i >= start && i < destination) {
            clockwise += distance[i];
        }
    }

    let counterClockwise = total - clockwise;
    return Math.min(clockwise, counterClockwise);
};

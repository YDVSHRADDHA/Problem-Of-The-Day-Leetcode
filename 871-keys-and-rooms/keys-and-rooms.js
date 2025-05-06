/**
 * @param {number[][]} rooms
 * @return {boolean}
 */
var canVisitAllRooms = function(rooms) {
    const visited = new Set();
    const queue = [0]; // Start from room 0

    while (queue.length > 0) {
        const room = queue.shift(); // Get the next room from the queue
        if (!visited.has(room)) {
            visited.add(room);
            for (const key of rooms[room]) {
                if (!visited.has(key)) {
                    queue.push(key); // Add unvisited room keys to the queue
                }
            }
        }
    }

    return visited.size === rooms.length;
};

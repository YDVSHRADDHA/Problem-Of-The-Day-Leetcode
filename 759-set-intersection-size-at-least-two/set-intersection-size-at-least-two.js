/**
 * @param {number[][]} intervals
 * @return {number}
 */
var intersectionSizeTwo = function(intervals) {
    // Sort by end asc, and start desc when end same
    intervals.sort((a, b) => 
        a[1] === b[1] ? b[0] - a[0] : a[1] - b[1]
    );

    let result = [];
    let a = -1, b = -1; // last two chosen nums

    for (let [l, r] of intervals) {
        let count = 0;

        if (a >= l) count++;
        if (b >= l) count++;

        if (count === 2) {
            // already satisfied
            continue;
        } 
        else if (count === 1) {
            // add one more: choose the largest possible => r
            let x = r;
            result.push(x);
            
            // update pointers
            a = b;
            b = x;
        } 
        else {
            // count === 0 → add two numbers: r-1 and r
            let x = r - 1, y = r;
            result.push(x);
            result.push(y);

            a = x;
            b = y;
        }
    }

    return result.length;
};

/**
 * @param {number} n
 * @param {number[]} batteries
 * @return {number}
 */
var maxRunTime = function(n, batteries) {
    let total = batteries.reduce((a, b) => a + b, 0);
    
    let low = 1;
    let high = Math.floor(total / n);
    
    while (low < high) {
        let mid = Math.ceil((low + high) / 2);
        let sum = 0;
        
        for (let battery of batteries) {
            sum += Math.min(battery, mid);
        }
        
        if (sum >= mid * n) {
            low = mid; // possible, try bigger
        } else {
            high = mid - 1; // not possible, try smaller
        }
    }
    
    return low;
};

/**
 * @param {number[]} arr
 * @return {boolean}
 */
var uniqueOccurrences = function(arr) {
   
    const freqMap = new Map();

    // Count frequencies
    for (let num of arr) {
        freqMap.set(num, (freqMap.get(num) || 0) + 1);
    }

    const occurrences = new Set();

    // Check if frequencies are unique
    for (let count of freqMap.values()) {
        if (occurrences.has(count)) {
            return false;
        }
        occurrences.add(count);
    }

    return true;
 
};
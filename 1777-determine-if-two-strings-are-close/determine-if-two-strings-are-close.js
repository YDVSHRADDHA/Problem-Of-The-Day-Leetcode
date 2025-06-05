/**
 * @param {string} word1
 * @param {string} word2
 * @return {boolean}
 */
var closeStrings = function(word1, word2) {
  
    if (word1.length !== word2.length) return false;

    const getFreqMap = (word) => {
        const map = new Map();
        for (let char of word) {
            map.set(char, (map.get(char) || 0) + 1);
        }
        return map;
    };

    const map1 = getFreqMap(word1);
    const map2 = getFreqMap(word2);

    // Check if both words have the same unique characters
    const keys1 = [...map1.keys()].sort().join('');
    const keys2 = [...map2.keys()].sort().join('');
    if (keys1 !== keys2) return false;

    // Compare sorted frequency counts
    const freq1 = [...map1.values()].sort((a, b) => a - b);
    const freq2 = [...map2.values()].sort((a, b) => a - b);

    return JSON.stringify(freq1) === JSON.stringify(freq2);
 
};
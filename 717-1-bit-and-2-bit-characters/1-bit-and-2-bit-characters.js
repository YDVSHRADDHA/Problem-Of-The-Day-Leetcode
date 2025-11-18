/**
 * @param {number[]} bits
 * @return {boolean}
 */
var isOneBitCharacter = function(bits) {
    let i = 0;
    let n = bits.length;
    
    while (i < n - 1) {
        if (bits[i] === 1) {
            i += 2;  // two-bit character
        } else {
            i += 1;  // one-bit character
        }
    }
    
    return i === n - 1;
};

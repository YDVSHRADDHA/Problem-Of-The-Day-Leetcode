 /**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function(haystack, needle) {
    if (needle === "") return 0; // Return 0 if needle is an empty string
    
    for (let i = 0; i <= haystack.length - needle.length; i++) {
        if (haystack.substring(i, i + needle.length) === needle) {
            return i; // Return the index of the first occurrence
        }
    }
    
    return -1; // Return -1 if the needle is not found
};

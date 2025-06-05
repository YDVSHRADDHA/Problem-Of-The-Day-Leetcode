/**
 * @param {string} s
 * @return {string}
 */
var removeStars = function(s) {
    const stack = [];
    for (const char of s) {
        if (char === '*') {
            stack.pop(); // remove the closest non-star char on the left
        } else {
            stack.push(char);
        }
    }
    return stack.join('');
};

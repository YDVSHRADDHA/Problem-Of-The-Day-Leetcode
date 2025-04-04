/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function(digits) {
    if (!digits || digits.length === 0) return [];

    const map = {
        '2': 'abc', '3': 'def', '4': 'ghi',
        '5': 'jkl', '6': 'mno', '7': 'pqrs',
        '8': 'tuv', '9': 'wxyz'
    };

    const result = [];

    const backtrack = (index, path) => {
        if (index === digits.length) {
            result.push(path);
            return;
        }

        const letters = map[digits[index]];
        for (let char of letters) {
            backtrack(index + 1, path + char);
        }
    };

    backtrack(0, '');
    return result;
};

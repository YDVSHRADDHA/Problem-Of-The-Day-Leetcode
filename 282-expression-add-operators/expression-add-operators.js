/**
 * @param {string} num
 * @param {number} target
 * @return {string[]}
 */
var addOperators = function(num, target) {
 
    const result = [];

    const backtrack = (index, path, evaluated, prevOperand) => {
        // If we've used up all digits
        if (index === num.length) {
            if (evaluated === target) {
                result.push(path);
            }
            return;
        }

        for (let i = index; i < num.length; i++) {
            // Prevent numbers with leading zero
            if (i !== index && num[index] === '0') break;

            const currStr = num.substring(index, i + 1);
            const currNum = Number(currStr);

            if (index === 0) {
                // First number, pick it without any operator
                backtrack(i + 1, currStr, currNum, currNum);
            } else {
                // Try '+'
                backtrack(i + 1, path + '+' + currStr, evaluated + currNum, currNum);
                // Try '-'
                backtrack(i + 1, path + '-' + currStr, evaluated - currNum, -currNum);
                // Try '*'
                backtrack(
                    i + 1,
                    path + '*' + currStr,
                    evaluated - prevOperand + prevOperand * currNum,
                    prevOperand * currNum
                );
            }
        }
    };

    backtrack(0, '', 0, 0);
    return result;
};

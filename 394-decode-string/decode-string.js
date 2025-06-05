var decodeString = function(s) {
    let stack = [];
    let currentNum = 0;
    let currentStr = '';

    for (let char of s) {
        if (!isNaN(char)) {
            // If it's a number, build the full number
            currentNum = currentNum * 10 + parseInt(char);
        } else if (char === '[') {
            // Push the current context and reset
            stack.push([currentStr, currentNum]);
            currentStr = '';
            currentNum = 0;
        } else if (char === ']') {
            // Pop from stack and build the string
            const [prevStr, num] = stack.pop();
            currentStr = prevStr + currentStr.repeat(num);
        } else {
            // Regular character
            currentStr += char;
        }
    }

    return currentStr;
};

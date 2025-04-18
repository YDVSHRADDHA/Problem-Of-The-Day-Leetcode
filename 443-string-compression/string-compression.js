/**
 * @param {character[]} chars
 * @return {number}
 */
var compress = function(chars) {
    let writeIndex = 0, readIndex = 0;
    
    while (readIndex < chars.length) {
        let currentChar = chars[readIndex];
        let count = 0;
        
        // Count the occurrences of the current character
        while (readIndex < chars.length && chars[readIndex] === currentChar) {
            count++;
            readIndex++;
        }
        
        // Write the character to the result
        chars[writeIndex++] = currentChar;
        
        // If the count is greater than 1, write the count as well
        if (count > 1) {
            let countStr = count.toString();
            for (let i = 0; i < countStr.length; i++) {
                chars[writeIndex++] = countStr[i];
            }
        }
    }
    
    return writeIndex;
};

/**
 * @param {number} n
 * @return {string}
 */
var countAndSay = function(n) {
    // Base case for n = 1
    let result = "1";  
    
    // Generate the sequence from 2 up to n
    for (let i = 2; i <= n; i++) {
        let temp = "";
        let count = 1;
        
        // Traverse through the previous result
        for (let j = 1; j < result.length; j++) {
            // If current digit is same as previous digit, increment count
            if (result[j] === result[j - 1]) {
                count++;
            } else {
                // Append the count and the previous digit to temp
                temp += count + result[j - 1];
                count = 1;  // Reset count for the new digit
            }
        }
        
        // Append the count and the last digit
        temp += count + result[result.length - 1];
        
        // Update result to the new string
        result = temp;
    }

    return result;
};

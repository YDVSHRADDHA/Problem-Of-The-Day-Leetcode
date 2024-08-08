/**
 * @param {string} s
 * @return {number}
 */
var scoreOfString = function(s) {
    // Initialize the score
    let score = 0;

    // Loop through the string and calculate the sum of absolute differences
    for (let i = 0; i < s.length - 1; i++) {
        // Calculate the absolute difference between adjacent characters
        let diff = Math.abs(s.charCodeAt(i) - s.charCodeAt(i + 1));
        // Add the difference to the score
        score += diff;
    }

    // Return the final score
    return score;
};

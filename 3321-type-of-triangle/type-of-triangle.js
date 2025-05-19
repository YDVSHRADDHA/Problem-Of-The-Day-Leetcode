/**
 * @param {number[]} nums
 * @return {string}
 */
var triangleType = function(nums) {
    const [a, b, c] = nums;

    // First, check triangle inequality rule
    if (a + b <= c || a + c <= b || b + c <= a) {
        return "none"; // It cannot form a triangle
    }

    // Now check type of triangle
    if (a === b && b === c) {
        return "equilateral";
    } else if (a === b || b === c || a === c) {
        return "isosceles";
    } else {
        return "scalene";
    }
};

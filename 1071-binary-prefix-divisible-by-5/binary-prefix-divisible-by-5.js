/**
 * @param {number[]} nums
 * @return {boolean[]}
 */
var prefixesDivBy5 = function(nums) {
    let rem = 0;
    let ans = [];

    for (let b of nums) {
        rem = (rem * 2 + b) % 5;
        ans.push(rem === 0);
    }

    return ans;
};
;
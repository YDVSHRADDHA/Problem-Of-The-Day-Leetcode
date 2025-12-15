/**
 * @param {number[]} prices
 * @return {number}
 */
var getDescentPeriods = function(prices) {
    let result = 0;
    let len = 0;

    for (let i = 0; i < prices.length; i++) {
        if (i > 0 && prices[i - 1] - prices[i] === 1) {
            len += 1;   // extend smooth descent
        } else {
            len = 1;    // reset
        }
        result += len;
    }

    return result;
};

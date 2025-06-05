/**
 * @param {number[]} prices
 * @param {number} fee
 * @return {number}
 */
var maxProfit = function(prices, fee) {
    let n = prices.length;
    let hold = -prices[0]; // bought stock
    let cash = 0; // no stock

    for (let i = 1; i < n; i++) {
        hold = Math.max(hold, cash - prices[i]);
        cash = Math.max(cash, hold + prices[i] - fee);
    }

    return cash;
};

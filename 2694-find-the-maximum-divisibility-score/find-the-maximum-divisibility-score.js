/**
 * 2644. Find the Maximum Divisibility Score
 *
 * For each divisor:
 *   - Count how many numbers in nums are divisible by it.
 *   - Track the maximum score.
 *   - If scores tie, choose the smaller divisor.
 */
var maxDivScore = function(nums, divisors) {
    let bestScore = -1;
    let bestDivisor = Infinity;

    for (let d of divisors) {
        let score = 0;

        // Count how many nums[j] are divisible by d
        for (let num of nums) {
            if (num % d === 0) score++;
        }

        // Update best score OR choose smaller divisor on tie
        if (score > bestScore || (score === bestScore && d < bestDivisor)) {
            bestScore = score;
            bestDivisor = d;
        }
    }

    return bestDivisor;
};

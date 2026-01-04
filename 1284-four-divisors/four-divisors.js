/**
 * @param {number[]} nums
 * @return {number}
 */
var sumFourDivisors = function(nums) {
    let answer = 0;

    for (let num of nums) {
        let divisorCount = 0;
        let divisorSum = 0;

        for (let i = 1; i * i <= num; i++) {
            if (num % i === 0) {
                let d1 = i;
                let d2 = num / i;

                if (d1 === d2) {
                    divisorCount += 1;
                    divisorSum += d1;
                } else {
                    divisorCount += 2;
                    divisorSum += d1 + d2;
                }

                // Stop early if more than 4 divisors
                if (divisorCount > 4) break;
            }
        }

        if (divisorCount === 4) {
            answer += divisorSum;
        }
    }

    return answer;
};

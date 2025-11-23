var maxSumDivThree = function(nums) {
    let sum = 0;
    let rem1 = [];
    let rem2 = [];

    for (let num of nums) {
        sum += num;
        if (num % 3 === 1) rem1.push(num);
        else if (num % 3 === 2) rem2.push(num);
    }

    // Sort to get smallest removals first
    rem1.sort((a, b) => a - b);
    rem2.sort((a, b) => a - b);

    if (sum % 3 === 0) return sum;

    let option = 0;

    if (sum % 3 === 1) {
        let removeOne = rem1.length > 0 ? rem1[0] : Infinity;
        let removeTwo = rem2.length > 1 ? rem2[0] + rem2[1] : Infinity;
        option = Math.min(removeOne, removeTwo);
    } else { 
        // sum % 3 === 2
        let removeOne = rem2.length > 0 ? rem2[0] : Infinity;
        let removeTwo = rem1.length > 1 ? rem1[0] + rem1[1] : Infinity;
        option = Math.min(removeOne, removeTwo);
    }

    return sum - option;
};

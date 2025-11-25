var smallestRepunitDivByK = function(k) {
    if (k % 2 === 0 || k % 5 === 0) return -1; // no solution

    let remainder = 1 % k;
    let length = 1;

    while (remainder !== 0) {
        remainder = (remainder * 10 + 1) % k;
        length++;
    }

    return length;
};

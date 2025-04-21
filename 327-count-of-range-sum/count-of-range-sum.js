function countRangeSum(nums, lower, upper) {
    const prefixSums = [0];
    for (let num of nums) {
        prefixSums.push(prefixSums[prefixSums.length - 1] + num);
    }

    function mergeSort(start, end) {
        if (end - start <= 1) return 0;

        const mid = Math.floor((start + end) / 2);
        let count = mergeSort(start, mid) + mergeSort(mid, end);

        let j = mid, k = mid, t = mid;
        const temp = [];
        let r = 0;

        for (let i = start; i < mid; i++) {
            while (k < end && prefixSums[k] - prefixSums[i] < lower) k++;
            while (j < end && prefixSums[j] - prefixSums[i] <= upper) j++;
            count += j - k;

            while (t < end && prefixSums[t] < prefixSums[i]) {
                temp[r++] = prefixSums[t++];
            }
            temp[r++] = prefixSums[i];
        }

        for (let i = 0; i < t - start; i++) {
            prefixSums[start + i] = temp[i];
        }

        return count;
    }

    return mergeSort(0, prefixSums.length);
}

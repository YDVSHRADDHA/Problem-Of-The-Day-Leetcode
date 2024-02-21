class Solution {
    public int rangeBitwiseAnd(int left, int right) {
       // Edge Case
        if (left == right) {
            return left;
        }
		// Note that 1 & 2 = 0, 3 & 4 = 0, 7 & 8 = 0
		// It can be found that if floor(log2(left)) != floor(log2(right)), we can return 0 directly
        int log2Left = (int) (Math.log(left) / Math.log(2));
        int log2Right = (int) (Math.log(right) / Math.log(2));
        if (log2Left != log2Right) {
            return 0;
        }
//         int andResult = left;
//         while (left != right) {
//             andResult &= left;
//             left++;
//         }
//         return andResult;
//     }
// }
// Brute Force
        long ans = left;
        for (long i = left + 1; i <= right; i++) {
            ans &= i;
        }
        return (int) ans;
    }
}

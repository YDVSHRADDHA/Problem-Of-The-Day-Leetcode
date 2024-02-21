class Solution {
    public int rangeBitwiseAnd(int left, int right) {
 int shift = 0;

        // Keep shifting right until left and right are equal
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }

        // Left shift back to get the final result
        return left << shift;
    }
}
/* 1st logic failed in time complexx!*/ 
//         int andResult = left;
//         while (left != right) {
//             andResult &= left;
//             left++;
//         }
//         return andResult;
//     }
// }


//  2nd logic
//         if (left == right) {
//             return left;
//         }
// 		// Note that 1 & 2 = 0, 3 & 4 = 0, 7 & 8 = 0
// 		// It can be found that if floor(log2(left)) != floor(log2(right)), we can return 0 directly
//         int log2Left = (int) (Math.log(left) / Math.log(2));
//         int log2Right = (int) (Math.log(right) / Math.log(2));
//         if (log2Left != log2Right) {
//             return 0;
//         }

// // Brute Force
//         long ans = left;
//         for (long i = left + 1; i <= right; i++) {
//             ans &= i;
//         }
//         return (int) ans;
//     }
// }
// 3rd
 
//         while (right > left) {
//             right = right & (right - 1);
//         }
//         return right & left;
//     }
// }
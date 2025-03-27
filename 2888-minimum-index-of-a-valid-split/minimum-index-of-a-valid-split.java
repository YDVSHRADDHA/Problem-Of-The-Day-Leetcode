import java.util.*;

class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();

        // Step 1: Find the candidate for dominant element (Moore’s Voting Algorithm)
        int candidate = -1, count = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }

        // Step 2: Validate if it's actually dominant
        int totalCount = 0;
        for (int num : nums) {
            if (num == candidate) totalCount++;
        }
        if (totalCount * 2 <= n) return -1; // No dominant element

        // Step 3: Find minimum index for valid split
        int leftCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) == candidate) leftCount++;

            int rightCount = totalCount - leftCount;
            if (leftCount * 2 > (i + 1) && rightCount * 2 > (n - (i + 1))) {
                return i;
            }
        }

        return -1; // No valid split found
    }
}


















// import java.util.*;

// class Solution {
//     public int minimumIndex(List<Integer> nums) {
//         int n = nums.size();
//         Map<Integer, Integer> freqMap = new HashMap<>();

//         // Step 1: Find dominant element
//         for (int num : nums) {
//             freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
//         }

//         int dominant = -1, totalCount = 0;
//         for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
//             if (entry.getValue() * 2 > n) {
//                 dominant = entry.getKey();
//                 totalCount = entry.getValue();
//                 break;
//             }
//         }

//         if (dominant == -1) return -1; // No dominant element exists

//         // Step 2: Prefix Frequency Count
//         int leftCount = 0;
//         for (int i = 0; i < n - 1; i++) {
//             if (nums.get(i) == dominant) leftCount++;

//             int rightCount = totalCount - leftCount; // Compute right count dynamically

//             // Step 3: Check the split condition
//             if (leftCount * 2 > (i + 1) && rightCount * 2 > (n - (i + 1))) {
//                 return i;
//             }
//         }

//         return -1; // No valid split found
//     }
// }








// import java.util.*;

// class Solution {
//     public int minimumIndex(List<Integer> nums) {
//         int n = nums.size();
//         Map<Integer, Integer> freqMap = new HashMap<>();

//         // Frequency count
//         for (int num : nums) {
//             freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
//         }

//         // Find the dominant element
//         int dominant = -1, totalCount = 0;
//         for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
//             if (entry.getValue() * 2 > n) { // Dominant condition check
//                 dominant = entry.getKey();
//                 totalCount = entry.getValue();
//                 break;
//             }
//         }

//         // If no dominant element, return -1
//         if (dominant == -1) return -1;

//         // Step 3: Find Minimum Split Index
//         int leftCount = 0;
//         for (int i = 0; i < n - 1; i++) {
//             if (nums.get(i) == dominant) leftCount++;

//             int rightCount = totalCount - leftCount;
//             if (leftCount * 2 > (i + 1) && rightCount * 2 > (n - (i + 1))) {
//                 return i;
//             }
//         }

//         return -1; // No valid split found
//     }
// }

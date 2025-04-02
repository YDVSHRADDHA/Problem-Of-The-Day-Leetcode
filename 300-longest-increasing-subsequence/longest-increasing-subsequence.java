import java.util.*;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();

        for (int num : nums) {
            int pos = Collections.binarySearch(lis, num); // Find the correct position
            if (pos < 0) {
                pos = -pos - 1; // Convert to the correct insertion index
            }

            if (pos == lis.size()) {
                lis.add(num); // Append if it's the largest element
            } else {
                lis.set(pos, num); // Replace to maintain the sequence
            }
        }
        
        return lis.size(); // Length of LIS
    }

    // public static void main(String[] args) {
    //     Solution sol = new Solution();
    //     int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    //     System.out.println(sol.lengthOfLIS(nums)); // Output: 4
    // }
}

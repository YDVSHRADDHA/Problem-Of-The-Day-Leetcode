import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>(); // List to maintain LIS elements

        for (int num : nums) {
            int pos = Collections.binarySearch(lis, num);
            if (pos < 0) {
                pos = -pos - 1; // Convert to insertion index
            }

            if (pos == lis.size()) {
                lis.add(num); // Append if larger than last element
            } else {
                lis.set(pos, num); // Replace smaller element
            }
        }
        return lis.size(); // Length of LIS
    }
}

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num; // Update first minimum
            } else if (num <= second) {
                second = num; // Update second minimum
            } else {
                return true; // Third number found
            }
        }
        return false;
    }
}

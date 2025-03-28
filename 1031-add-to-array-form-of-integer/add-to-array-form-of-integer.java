import java.util.*;

class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new ArrayList<>();
        int i = num.length - 1;
        int carry = 0;

        while (i >= 0 || k > 0) {
            if (i >= 0) {
                k += num[i]; // Add num[i] to k
                i--;
            }
            result.add(k % 10); // Store the last digit
            k /= 10; // Remove the last digit (equivalent to carry)
        }

        // Reverse to get the correct order
        Collections.reverse(result);
        return result;
    }
}

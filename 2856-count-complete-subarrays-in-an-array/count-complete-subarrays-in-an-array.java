import java.util.*;

public class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> allDistinct = new HashSet<>();
        for (int num : nums) {
            allDistinct.add(num);
        }
        int totalDistinct = allDistinct.size();
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> subDistinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                subDistinct.add(nums[j]);
                if (subDistinct.size() == totalDistinct) {
                    count++;
                }
            }
        }
        return count;
    }
}

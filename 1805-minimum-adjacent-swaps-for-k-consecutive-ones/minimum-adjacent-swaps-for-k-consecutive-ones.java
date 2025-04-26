import java.util.*;

public class Solution {
    public int minMoves(int[] nums, int k) {
        List<Integer> ones = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                ones.add(i - ones.size());
            }
        }
        
        int[] prefix = new int[ones.size() + 1];
        for (int i = 0; i < ones.size(); i++) {
            prefix[i + 1] = prefix[i] + ones.get(i);
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i + k <= ones.size(); i++) {
            int j = i + k - 1;
            int mid = (i + j) / 2;
            int median = ones.get(mid);
            
            int left = median * (mid - i) - (prefix[mid] - prefix[i]);
            int right = (prefix[j + 1] - prefix[mid + 1]) - median * (j - mid);
            
            res = Math.min(res, left + right);
        }
        
        return res;
    }
}

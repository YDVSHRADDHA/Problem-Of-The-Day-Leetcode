import java.util.*;

public class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Long> freqMap = new HashMap<>();
        freqMap.put(0, 1L);  // prefix = 0 at the start

        long count = 0;
        int prefix = 0;

        for (int num : nums) {
            if (num % modulo == k) prefix++;

            int currMod = prefix % modulo;
            // normalize (currMod - k + modulo) % modulo to avoid negative keys
            int target = (currMod - k + modulo) % modulo;

            count += freqMap.getOrDefault(target, 0L);
            freqMap.put(currMod, freqMap.getOrDefault(currMod, 0L) + 1);
        }

        return count;
    }
}

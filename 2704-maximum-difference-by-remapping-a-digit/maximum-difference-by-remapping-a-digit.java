class Solution {
    public int minMaxDifference(int num) {
        String s = String.valueOf(num);
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        // Try all digits from 0 to 9 as the source digit
        for (char src = '0'; src <= '9'; src++) {
            for (char tgt = '0'; tgt <= '9'; tgt++) {
                if (src == tgt) continue; // Skip if same digit
                String mapped = s.replace(src, tgt);
                int value = Integer.parseInt(mapped);
                maxVal = Math.max(maxVal, value);
                minVal = Math.min(minVal, value);
            }
        }

        return maxVal - minVal;
    }
}

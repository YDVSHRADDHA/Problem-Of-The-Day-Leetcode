class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) return result;
        
        int start = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {  // Break in sequence
                if (start == nums[i - 1]) {
                    result.add(String.valueOf(start));  // Single element range
                } else {
                    result.add(start + "->" + nums[i - 1]);  // Range format
                }
                start = nums[i];  // Start new range
            }
        }

        // Add the last range
        if (start == nums[nums.length - 1]) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + nums[nums.length - 1]);
        }

        return result;
    } 
    }
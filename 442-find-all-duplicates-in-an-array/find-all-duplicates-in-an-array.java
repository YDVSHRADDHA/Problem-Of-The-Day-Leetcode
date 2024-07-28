class Solution {
    public List<Integer> findDuplicates(int[] nums) {
         Set<Integer> set = new HashSet<>();
            List<Integer> duplicates = new ArrayList<>();
          for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                 duplicates.add(nums[i]);
            }
             set.add(nums[i]);
          }

             return duplicates;
    }
}
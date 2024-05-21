class Solution {
      public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsets(int index, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset to the result list
        result.add(new ArrayList<>(current));

        // Iterate through the array starting from the current index
        for (int i = index; i < nums.length; i++) {
            // Include the element nums[i] in the current subset
            current.add(nums[i]);
            // Recursively generate subsets including this element
            generateSubsets(i + 1, nums, current, result);
            // Backtrack and remove the element to explore other subsets
            current.remove(current.size() - 1);
        }
    }
}
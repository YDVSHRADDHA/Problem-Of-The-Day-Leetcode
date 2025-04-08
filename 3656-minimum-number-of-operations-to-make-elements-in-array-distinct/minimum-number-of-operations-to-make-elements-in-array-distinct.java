class Solution {
    public int minimumOperations(int[] nums) {
        int operations = 0;

        while (true) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            if (set.size() == nums.length) {
                break; // Already distinct
            }

            operations++;

            int newSize = Math.max(0, nums.length - 3);
            int[] temp = new int[newSize];

            for (int i = 0; i < newSize; i++) {
                temp[i] = nums[i + 3];
            }

            nums = temp;
        }

        return operations;
    }
}

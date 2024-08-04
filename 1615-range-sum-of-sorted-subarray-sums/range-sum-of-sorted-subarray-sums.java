class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
    ArrayList<Integer> s = new ArrayList<Integer>();
        for(int i =0; i <n; i++)
        {
            int sum = 0;
          
            // Iterate through all indices ahead of the current index.
              for(int j = i; j<n; j++){
                sum += nums[j];
                s.add(sum);

            }
        }
          // Sort all subarray sum values in increasing order.
        Collections.sort(s);

           // Find the sum of all values between left and right.
        int rangeSum = 0, mod = (int) 1e9 + 7;
        for (int i = left - 1; i <= right - 1; i++) {
            rangeSum = (rangeSum + s.get(i)) % mod;
        }
        return rangeSum;
    }
}
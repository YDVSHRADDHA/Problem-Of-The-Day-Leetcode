class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        
        for (int num = low; num <= high; num++) {
            String s = String.valueOf(num);
            int length = s.length();
            
            if (length % 2 == 0) {  // Check even length
                int half = length / 2;
                int sum1 = 0, sum2 = 0;
                
                // Sum of first half
                for (int i = 0; i < half; i++) {
                    sum1 += s.charAt(i) - '0';
                }
                
                // Sum of second half
                for (int i = half; i < length; i++) {
                    sum2 += s.charAt(i) - '0';
                }
                
                if (sum1 == sum2) {
                    count++;
                }
            }
        }
        
        return count;
    }
}

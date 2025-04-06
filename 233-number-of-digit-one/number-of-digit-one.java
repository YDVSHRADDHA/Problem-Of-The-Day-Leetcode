class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        
        for (long i = 1; i <= n; i *= 10) {
            long left = n / (i * 10);
            long curr = (n / i) % 10;
            long right = n % i;
            
            if (curr == 0) {
                count += left * i;
            } else if (curr == 1) {
                count += left * i + right + 1;
            } else {
                count += (left + 1) * i;
            }
        }
        
        return count;
    }
}

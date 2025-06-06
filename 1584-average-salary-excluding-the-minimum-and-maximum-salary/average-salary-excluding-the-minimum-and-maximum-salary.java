class Solution {
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        for (int s : salary) {
            sum += s;
            if (s < min) min = s;
            if (s > max) max = s;
        }
        
        // Exclude min and max from sum, then divide by (length - 2)
        return (double)(sum - min - max) / (salary.length - 2);
    }
}

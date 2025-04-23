 
public class Solution {
    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
            int digitSum = getDigitSum(i);
            map.put(digitSum, map.getOrDefault(digitSum, 0) + 1);
        }
        
        int maxSize = 0;
        for (int size : map.values()) {
            maxSize = Math.max(maxSize, size);
        }

        int count = 0;
        for (int size : map.values()) {
            if (size == maxSize) {
                count++;
            }
        }

        return count;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> set = new TreeSet<>();
        int n = digits.length;
        
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue; // hundred's digit cannot be 0
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = 0; k < n; k++) {
                    if (i == k || j == k) continue;
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (num % 2 == 0) {
                        set.add(num);
                    }
                }
            }
        }

        int[] res = new int[set.size()];
        int idx = 0;
        for (int num : set) {
            res[idx++] = num;
        }
        return res;
    }
}

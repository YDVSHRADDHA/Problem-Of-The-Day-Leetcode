class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0; // index of current best suffix
        int j = 1; // index of the next suffix to compare
        int k = 0; // offset for character comparison

        while (j + k < n) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            } else if (s.charAt(i + k) < s.charAt(j + k)) {
                // j's suffix is better, so update i
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            } else {
                // i's suffix is still better
                j = j + k + 1;
                k = 0;
            }
        }

        return s.substring(i);
    }
}

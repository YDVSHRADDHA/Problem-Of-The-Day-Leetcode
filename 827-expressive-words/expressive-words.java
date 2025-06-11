class Solution {
    public int expressiveWords(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isStretchy(s, word)) count++;
        }
        return count;
    }

    private boolean isStretchy(String s, String word) {
        int i = 0, j = 0;
        int n = s.length(), m = word.length();

        while (i < n && j < m) {
            if (s.charAt(i) != word.charAt(j)) return false;

            int lenS = 1, lenW = 1;

            while (i + lenS < n && s.charAt(i + lenS) == s.charAt(i)) lenS++;
            while (j + lenW < m && word.charAt(j + lenW) == word.charAt(j)) lenW++;

            if (lenS < lenW) return false;
            if (lenS != lenW && lenS < 3) return false;

            i += lenS;
            j += lenW;
        }

        return i == n && j == m;
    }
}

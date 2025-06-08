public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // Count characters in s1 and first window of s2
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        // Check if initial window matches
        if (matches(s1Count, s2Count)) return true;

        // Slide the window
        for (int i = s1.length(); i < s2.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++; // Add new char
            s2Count[s2.charAt(i - s1.length()) - 'a']--; // Remove old char

            if (matches(s1Count, s2Count)) return true;
        }

        return false;
    }

    private boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}

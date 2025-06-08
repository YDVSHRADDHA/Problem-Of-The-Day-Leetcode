public class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] licenseCount = getCharCount(licensePlate);
        String result = null;

        for (String word : words) {
            if (isCompletingWord(word, licenseCount)) {
                if (result == null || word.length() < result.length()) {
                    result = word;
                }
            }
        }

        return result;
    }

    // Helper method to get character count from license plate
    private int[] getCharCount(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                count[Character.toLowerCase(c) - 'a']++;
            }
        }
        return count;
    }

    // Check if word meets or exceeds all licensePlate character requirements
    private boolean isCompletingWord(String word, int[] licenseCount) {
        int[] wordCount = new int[26];
        for (char c : word.toCharArray()) {
            wordCount[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (wordCount[i] < licenseCount[i]) {
                return false;
            }
        }

        return true;
    }
}

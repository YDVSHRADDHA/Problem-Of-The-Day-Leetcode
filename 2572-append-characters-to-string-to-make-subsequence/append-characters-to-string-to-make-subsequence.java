class Solution {
    public int appendCharacters(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        int sLength = s.length();
        int tLength = t.length();
        
        // Traverse through both strings to find matching characters
        while (sIndex < sLength && tIndex < tLength) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                tIndex++; // Move to the next character in t
            }
            sIndex++; // Always move to the next character in s
        }
        
        // The remaining characters in t that need to be appended
        return tLength - tIndex;
    }
}

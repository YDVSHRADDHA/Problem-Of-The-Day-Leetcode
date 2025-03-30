class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim(); // Remove leading and trailing spaces
        int lastIndex = s.lastIndexOf(' '); // Find the last space
        return s.length() - lastIndex - 1; // Length of the last word 
    }
}
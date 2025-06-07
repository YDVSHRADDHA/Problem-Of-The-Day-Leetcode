class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        // Convert each word to its numeric value
        int firstVal = wordToNumber(firstWord);
        int secondVal = wordToNumber(secondWord);
        int targetVal = wordToNumber(targetWord);
        
        // Check if the sum equals the target value
        return firstVal + secondVal == targetVal;
    }

    // Helper function to convert word to number
    private int wordToNumber(String word) {
        StringBuilder numStr = new StringBuilder();
        for (char c : word.toCharArray()) {
            // 'a' maps to 0, 'b' to 1, ..., 'j' to 9
            numStr.append(c - 'a');
        }
        return Integer.parseInt(numStr.toString());
    }
}

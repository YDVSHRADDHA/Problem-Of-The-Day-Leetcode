class Solution {
    public int scoreOfString(String s) {
        // Initialize the score
        int score = 0;

        // Loop through the string and calculate the sum of absolute differences
        for (int i = 0; i < s.length() - 1; i++) {
            // Calculate the absolute difference between adjacent characters
            int diff = Math.abs(s.charAt(i) - s.charAt(i + 1));
            // Add the difference to the score
            score += diff;
        }

        // Return the final score
        return score;
    }
}
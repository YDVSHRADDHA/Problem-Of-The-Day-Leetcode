public class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;

        // Arrays to count unmatched digits (0-9)
        int[] secretCount = new int[10];
        int[] guessCount = new int[10];

        // First pass: identify bulls and prepare counts for cows
        for (int i = 0; i < secret.length(); i++) {
            char sChar = secret.charAt(i);
            char gChar = guess.charAt(i);

            if (sChar == gChar) {
                bulls++;
            } else {
                secretCount[sChar - '0']++;
                guessCount[gChar - '0']++;
            }
        }

        // Second pass: count cows (unmatched digits that overlap)
        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretCount[i], guessCount[i]);
        }

        return bulls + "A" + cows + "B";
    }
}

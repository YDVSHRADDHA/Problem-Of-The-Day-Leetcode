import java.util.*;

class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> candidates = new ArrayList<>(Arrays.asList(wordlist));
        
        for (int i = 0; i < 30; i++) {
            String guess = getBestGuess(candidates); // Select the best guess
            int matches = master.guess(guess);

            // If we found the correct word
            if (matches == 6) {
                return;
            }

            // Narrow down the candidates based on the number of matches with the guess
            List<String> newCandidates = new ArrayList<>();
            for (String word : candidates) {
                if (getMatches(word, guess) == matches) {
                    newCandidates.add(word);
                }
            }

            // Update the candidate list
            candidates = newCandidates;
        }
    }

    // Function to calculate the number of exact matches between two words
    private int getMatches(String word1, String word2) {
        int matches = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }

    // Function to get the best guess from the remaining candidates
    private String getBestGuess(List<String> candidates) {
        String bestGuess = candidates.get(0);
        int minMaxSize = Integer.MAX_VALUE;

        // Try each word in the candidate list and simulate the outcome
        for (String guess : candidates) {
            // Count how many words belong to each match group
            Map<Integer, Integer> matchGroupCount = new HashMap<>();
            for (String word : candidates) {
                int matches = getMatches(guess, word);
                matchGroupCount.put(matches, matchGroupCount.getOrDefault(matches, 0) + 1);
            }

            // Find the worst-case size (the largest group after this guess)
            int maxGroupSize = Collections.max(matchGroupCount.values());

            // We want to minimize the size of the largest group
            if (maxGroupSize < minMaxSize) {
                minMaxSize = maxGroupSize;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }
}

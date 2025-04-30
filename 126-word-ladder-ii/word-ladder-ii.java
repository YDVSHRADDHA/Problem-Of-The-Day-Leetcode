import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // We make a set of words from the wordList to easily check if a word exists.
        Set<String> wordSet = new HashSet<>(wordList);
        
        // This will hold all the possible sequences from beginWord to endWord.
        List<List<String>> result = new ArrayList<>();
        
        // If the endWord is not in the wordSet, we can't make a transformation, so return an empty result.
        if (!wordSet.contains(endWord)) {
            return result;
        }
        
        // This will help us know where each word came from during the search.
        Map<String, List<String>> graph = new HashMap<>();
        
        // Set for the current level of words we are looking at. Start with the beginWord.
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        
        // We have not yet found a way to reach the endWord.
        boolean foundEnd = false;
        
        // We go through the words level by level, from beginWord to endWord.
        while (!currentLevel.isEmpty() && !foundEnd) {
            // Before checking the next level, we remove the words we already visited from the wordSet.
            wordSet.removeAll(currentLevel);
            
            // Set for the next level of words we will explore.
            Set<String> nextLevel = new HashSet<>();
            
            // Look at each word in the current level and see what other words we can make by changing one letter.
            for (String word : currentLevel) {
                // Convert the word into an array of letters to change each letter one by one.
                char[] wordChars = word.toCharArray();
                
                // Check each letter in the word.
                for (int i = 0; i < wordChars.length; i++) {
                    char originalChar = wordChars[i]; // Save the original letter, so we can put it back later.
                    
                    // Try replacing the letter with every letter from 'a' to 'z'.
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[i] = c; // Replace the current letter with a new one.
                        String newWord = new String(wordChars); // Create a new word with the replaced letter.
                        
                        // If the new word is in the wordSet (meaning it's in our word list), we can explore it.
                        if (wordSet.contains(newWord)) {
                            nextLevel.add(newWord); // Add the new word to the next level.
                            
                            // Add the current word to the graph so we know where we came from.
                            graph.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
                            
                            // If we found the endWord, set foundEnd to true.
                            if (newWord.equals(endWord)) {
                                foundEnd = true;
                            }
                        }
                    }
                    
                    // After trying all letters, put the original letter back.
                    wordChars[i] = originalChar;
                }
            }
            
            // Now, move on to the next level of words to explore.
            currentLevel = nextLevel;
        }
        
        // If we found the endWord, we need to trace back all the possible paths to get there.
        if (foundEnd) {
            List<String> path = new LinkedList<>();
            path.add(endWord); // Start with the endWord.
            
            // Backtrack to find all the paths.
            backtrack(result, path, graph, beginWord, endWord);
        }
        
        // Return all the possible paths from beginWord to endWord.
        return result;
    }
    
    // This function helps to backtrack and find all possible paths from the endWord to the beginWord.
    private void backtrack(List<List<String>> result, List<String> path, Map<String, List<String>> graph, String beginWord, String currentWord) {
        // If we reach the beginWord, we have found a valid path, so add it to the result.
        if (currentWord.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath); // Reverse the path to go from beginWord to endWord.
            result.add(validPath); // Add the path to the result.
            return;
        }
        
        // If the current word has any previous words in the graph (possible previous steps), backtrack.
        if (graph.get(currentWord) != null) {
            for (String prev : graph.get(currentWord)) {
                path.add(prev); // Add the previous word to the current path.
                backtrack(result, path, graph, beginWord, prev); // Continue backtracking from the previous word.
                path.remove(path.size() - 1); // Remove the last word to try the next one.
            }
        }
    }
}

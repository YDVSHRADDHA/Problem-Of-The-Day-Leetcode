import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a set for quick lookup
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If the endWord is not in the dictionary, return 0 immediately
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // BFS setup: Queue will store the current word and the number of transformations taken to reach it
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // To avoid re-processing the same word
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        // Keep track of the number of transformations
        int steps = 1;  // Start from beginWord
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all words at the current level (current transformation)
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // Try changing each letter in the current word
                char[] wordArray = currentWord.toCharArray();
                for (int j = 0; j < wordArray.length; j++) {
                    char originalChar = wordArray[j];
                    
                    // Try all possible letters for position j
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[j] = c;
                        String newWord = new String(wordArray);
                        
                        // If the new word is in the wordList and not visited yet
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            // If we reach the endWord, return the number of steps taken
                            if (newWord.equals(endWord)) {
                                return steps + 1;
                            }
                            
                            // Add the new word to the queue and mark it as visited
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                    
                    // Restore the original character after trying all possibilities
                    wordArray[j] = originalChar;
                }
            }
            
            // After processing the current level, increment the step count
            steps++;
        }
        
        // If no transformation sequence exists, return 0
        return 0;
    }
}

import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0; // endWord must be in wordList
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        int level = 1; // Start with beginWord, so level = 1
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // Try all possible one-letter transformations
                char[] wordChars = currentWord.toCharArray();
                
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        
                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }
                        
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // Avoid revisiting
                        }
                    }
                    
                    wordChars[j] = originalChar; // Restore original char
                }
            }
            
            level++; // Increase level after processing current layer
        }
        
        return 0; // If we cannot reach endWord
    }
}

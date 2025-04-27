import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        
        if (!wordSet.contains(endWord)) {
            return result;
        }
        
        // Map to record from where we reached each word
        Map<String, List<String>> graph = new HashMap<>();
        
        // Set for current level
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        
        boolean foundEnd = false;
        
        while (!currentLevel.isEmpty() && !foundEnd) {
            // Remove visited words from the wordSet
            wordSet.removeAll(currentLevel);
            Set<String> nextLevel = new HashSet<>();
            
            for (String word : currentLevel) {
                char[] wordChars = word.toCharArray();
                
                for (int i = 0; i < wordChars.length; i++) {
                    char originalChar = wordChars[i];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[i] = c;
                        String newWord = new String(wordChars);
                        
                        if (wordSet.contains(newWord)) {
                            nextLevel.add(newWord);
                            
                            graph.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
                            
                            if (newWord.equals(endWord)) {
                                foundEnd = true;
                            }
                        }
                    }
                    
                    wordChars[i] = originalChar;
                }
            }
            
            currentLevel = nextLevel;
        }
        
        if (foundEnd) {
            List<String> path = new LinkedList<>();
            path.add(endWord);
            backtrack(result, path, graph, beginWord, endWord);
        }
        
        return result;
    }
    
    private void backtrack(List<List<String>> result, List<String> path, Map<String, List<String>> graph, String beginWord, String currentWord) {
        if (currentWord.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }
        
        if (graph.get(currentWord) != null) {
            for (String prev : graph.get(currentWord)) {
                path.add(prev);
                backtrack(result, path, graph, beginWord, prev);
                path.remove(path.size() - 1);
            }
        }
    }
}

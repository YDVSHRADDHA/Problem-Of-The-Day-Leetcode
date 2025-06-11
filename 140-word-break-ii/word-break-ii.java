class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // Convert wordDict to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        // Memo to store results for each substring start index
        Map<Integer, List<String>> memo = new HashMap<>();
        return backtrack(s, 0, wordSet, memo);
    }
    
    private List<String> backtrack(String s, int start, Set<String> wordSet, Map<Integer, List<String>> memo) {
        // If result for this start index is cached, return it
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        
        List<String> results = new ArrayList<>();
        
        // If we've reached the end of the string, add empty string as base for building sentences
        if (start == s.length()) {
            results.add("");
        }
        
        // Try all possible end positions from start+1 to s.length()
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word)) {
                // Get all sentences from the remaining substring
                List<String> sublist = backtrack(s, end, wordSet, memo);
                for (String sub : sublist) {
                    // If sub is empty, just add current word
                    String sentence = sub.isEmpty() ? word : word + " " + sub;
                    results.add(sentence);
                }
            }
        }
        
        // Cache and return
        memo.put(start, results);
        return results;
    }
}

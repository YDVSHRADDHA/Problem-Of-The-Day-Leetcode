class StreamChecker {
    
    // Trie Node class
    class TrieNode {
        TrieNode[] children = new TrieNode[26]; // for lowercase English letters
        boolean isWord = false;  // to mark the end of a word
    }

    private TrieNode root;  // root of the Trie
    private Deque<Character> stream;  // stores the current stream of characters

    public StreamChecker(String[] words) {
        root = new TrieNode();
        stream = new ArrayDeque<>();
        
        // Build the Trie by inserting reversed words
        for (String word : words) {
            TrieNode node = root;
            // Insert reversed word into Trie
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isWord = true;  // Mark the end of the word
        }
    }

    public boolean query(char letter) {
        // Add the current letter to the stream
        stream.addFirst(letter); // Add the letter to the front of the stream

        // Start at the root and traverse the Trie based on the current stream
        TrieNode node = root;
        
        // Traverse the stream in reverse order
        for (char ch : stream) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false; // no matching suffix
            }
            node = node.children[index];

            if (node.isWord) {
                return true;  // If a word ends here, return true
            }
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */

class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int idx = -1;
    }

    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        // Build Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer, i);
        }

        int[] ans = new int[wordsQuery.length];

        // Answer queries
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String[] words, int idx) {

        String s = words[idx];
        TrieNode node = root;

        // update root answer too
        update(node, words, idx);

        for (int i = s.length() - 1; i >= 0; i--) {

            int c = s.charAt(i) - 'a';

            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            node = node.child[c];

            update(node, words, idx);
        }
    }

    private void update(TrieNode node, String[] words, int idx) {

        if (node.idx == -1) {
            node.idx = idx;
            return;
        }

        String oldWord = words[node.idx];
        String newWord = words[idx];

        if (newWord.length() < oldWord.length()) {
            node.idx = idx;
        }
    }

    private int search(String query) {

        TrieNode node = root;

        for (int i = query.length() - 1; i >= 0; i--) {

            int c = query.charAt(i) - 'a';

            if (node.child[c] == null) {
                break;
            }

            node = node.child[c];
        }

        return node.idx;
    }
}
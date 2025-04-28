import java.util.*;

class Encrypter {

    private Map<Character, Integer> keyIndexer;
    private char[] keys;
    private Map<String, List<Character>> valuesIndexer;
    private String[] values;
    private Node root;

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        this.keys = keys;
        this.keyIndexer = keyIndexMapper(keys);
        this.values = values;
        this.valuesIndexer = valuesIndexMapper(values);
        this.root = new Node();
        initialiseDictionary(dictionary);
    }

    private Map<Character, Integer> keyIndexMapper(char[] keys) {
        Map<Character, Integer> keyIndexer = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            if (!keyIndexer.containsKey(keys[i])) {
                keyIndexer.put(keys[i], i);
            }
        }
        return keyIndexer;
    }

    private Map<String, List<Character>> valuesIndexMapper(String[] values) {
        Map<String, List<Character>> valuesIndexer = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            var list = valuesIndexer.getOrDefault(values[i], new ArrayList<Character>());
            list.add(keys[i]);
            valuesIndexer.put(values[i], list);
        }
        return valuesIndexer;
    }

    private Set<String> dictionaryMapper(String[] dictionary) {
        Set<String> set = new HashSet<>();
        for (String str : dictionary) {
            set.add(str);
        }
        return set;
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (char c : word1.toCharArray()) {
            if (keyIndexer.containsKey(c)) {
                sb.append(values[keyIndexer.get(c)]);
            } else {
                return "";
            }
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return getDecryptedWordsCount(this.root, word2, 0);
    }

    private class Node {
        private Map<Character, Node> nodes;
        private boolean isWord;

        public Node() {
            this.nodes = new HashMap<>();
        }

        public Node getNode(char c) {
            return this.nodes.get(c);
        }

        public Node getOrAddNode(char c) {
            Node node = this.getNode(c);
            return node != null ? node : addNode(c);
        }

        public Node addNode(char c) {
            Node node = new Node();
            this.nodes.put(c, node);
            return node;
        }

        public void setIsWord() {
            this.isWord = true;
        }

        public boolean isWord() {
            return this.isWord;
        }

        @Override
        public String toString() {
            return "Nodes: " + this.nodes;
        }
    }

    private void initialiseDictionary(String[] words) {
        for (String word : words) {
            this.insertWordToDictionary(word);
        }
    }

    private void insertWordToDictionary(String word) {
        Node node = this.root;
        for (int i = 0; i < word.length(); i++) {
            node = node.getOrAddNode(word.charAt(i));
        }
        node.setIsWord();
    }

    private int getDecryptedWordsCount(Node root, String encrypted, int startingIndex) {
        if (encrypted.length() <= startingIndex) {
            return root.isWord() ? 1 : 0;
        }

        List<Character> chars = decryptToChars(encrypted.substring(startingIndex, startingIndex + 2));
        int count = 0;
        for (char ch : chars) {
            Node nextNode = root.getNode(ch);
            if (nextNode != null) {
                count += getDecryptedWordsCount(nextNode, encrypted, startingIndex + 2);
            }
        }
        return count;
    }

    private List<Character> decryptToChars(String str) {
        return this.valuesIndexer.getOrDefault(str, new ArrayList<Character>());
    }
}

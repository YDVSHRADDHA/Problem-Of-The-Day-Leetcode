class MagicDictionary {
    private List<String> wordList;

    public MagicDictionary() {
        wordList = new ArrayList<>();
    }

    public void buildDict(String[] dictionary) {
        wordList.addAll(Arrays.asList(dictionary));
    }

    public boolean search(String searchWord) {
        for (String word : wordList) {
            if (word.length() != searchWord.length()) continue;
            
            int diffCount = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != searchWord.charAt(i)) {
                    diffCount++;
                }
                if (diffCount > 1) break;
            }

            if (diffCount == 1) return true;
        }
        return false;
    }
}

class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> freq = new HashMap<>();
        
        for (String word : (s1 + " " + s2).split(" ")) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (String word : freq.keySet()) {
            if (freq.get(word) == 1) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }
}

class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
  
    Map<String, Integer> countMap = new HashMap<>();
    int max = 0;

    for (int i = 0; i <= s.length() - minSize; i++) {
        String sub = s.substring(i, i + minSize);
        if (countUnique(sub) <= maxLetters) {
            countMap.put(sub, countMap.getOrDefault(sub, 0) + 1);
            max = Math.max(max, countMap.get(sub));
        }
    }

    return max;
}

private int countUnique(String str) {
    Set<Character> set = new HashSet<>();
    for (char c : str.toCharArray()) {
        set.add(c);
    }
    return set.size();
}
 
}
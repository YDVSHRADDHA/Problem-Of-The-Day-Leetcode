class Solution {
   public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    if (s.length() < p.length()) return result;

    int[] pCount = new int[26], sCount = new int[26];

    // Initialize frequency arrays
    for (char c : p.toCharArray()) {
        pCount[c - 'a']++;
    }

    for (int i = 0; i < p.length(); i++) {
        sCount[s.charAt(i) - 'a']++;
    }

    if (Arrays.equals(pCount, sCount)) {
        result.add(0);
    }

    // Slide the window
    for (int i = p.length(); i < s.length(); i++) {
        sCount[s.charAt(i) - 'a']++;
        sCount[s.charAt(i - p.length()) - 'a']--;

        if (Arrays.equals(pCount, sCount)) {
            result.add(i - p.length() + 1);
        }
    }

    return result;
}

}
class Solution {
    public int maxFreqSum(String s) {
      Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        Map<Character, Integer> vowelCount = new HashMap<>();
        Map<Character, Integer> consonantCount = new HashMap<>();

        // Count frequencies
        for (char ch : s.toCharArray()) {
            if (vowels.contains(ch)) {
                vowelCount.put(ch, vowelCount.getOrDefault(ch, 0) + 1);
            } else {
                consonantCount.put(ch, consonantCount.getOrDefault(ch, 0) + 1);
            }
        }

        // Find max frequency among vowels
        int maxVowel = 0;
        for (int freq : vowelCount.values()) {
            if (freq > maxVowel) {
                maxVowel = freq;
            }
        }

        // Find max frequency among consonants
        int maxConsonant = 0;
        for (int freq : consonantCount.values()) {
            if (freq > maxConsonant) {
                maxConsonant = freq;
            }
        }

        return maxVowel + maxConsonant;
    }
}
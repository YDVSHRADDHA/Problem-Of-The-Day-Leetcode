import java.util.*;

public class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> mapCharToWord = new HashMap<>();
        Map<String, Character> mapWordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (mapCharToWord.containsKey(c)) {
                if (!mapCharToWord.get(c).equals(word)) return false;
            } else {
                if (mapWordToChar.containsKey(word)) return false;

                mapCharToWord.put(c, word);
                mapWordToChar.put(word, c);
            }
        }

        return true;
    }
}

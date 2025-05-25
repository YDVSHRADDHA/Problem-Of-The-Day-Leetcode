public class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        int length = 0;
        boolean hasMiddle = false;

        for (String word : map.keySet()) {
            String rev = new StringBuilder(word).reverse().toString();

            if (!word.equals(rev)) {
                if (map.containsKey(rev)) {
                    int pairs = Math.min(map.get(word), map.get(rev));
                    length += 4 * pairs;
                    map.put(word, map.get(word) - pairs);
                    map.put(rev, map.get(rev) - pairs);
                }
            } else {
                int count = map.get(word);
                length += 4 * (count / 2);
                if (count % 2 == 1) hasMiddle = true;
            }
        }

        if (hasMiddle) length += 2;

        return length;
    }
}

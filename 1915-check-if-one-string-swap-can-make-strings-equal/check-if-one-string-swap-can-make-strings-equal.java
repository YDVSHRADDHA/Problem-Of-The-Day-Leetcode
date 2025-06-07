public class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        // If strings are already equal
        if (s1.equals(s2)) return true;

        int n = s1.length();
        List<Integer> diffIndices = new ArrayList<>();

        // Find indices where characters differ
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffIndices.add(i);
                if (diffIndices.size() > 2) return false; // More than 2 differences = not fixable with 1 swap
            }
        }

        // Must have exactly 2 different indices and swapping makes them equal
        return diffIndices.size() == 2 &&
               s1.charAt(diffIndices.get(0)) == s2.charAt(diffIndices.get(1)) &&
               s1.charAt(diffIndices.get(1)) == s2.charAt(diffIndices.get(0));
    }
}

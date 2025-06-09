public class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        int n = words.length;

        for (int i = 0; i < n; i++) {
            String str1 = words[i];
            for (int j = i + 1; j < n; j++) {
                String str2 = words[j];
                if (isPrefixAndSuffix(str1, str2)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPrefixAndSuffix(String str1, String str2) {
        return str2.startsWith(str1) && str2.endsWith(str1);
    }

     
}

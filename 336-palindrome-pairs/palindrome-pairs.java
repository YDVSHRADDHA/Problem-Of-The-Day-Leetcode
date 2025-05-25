class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> wordToIndex = new HashMap<>();  
        
        // Step 1: Store all words with their indices
        for (int i = 0; i < words.length; i++) {
            wordToIndex.put(words[i], i);
        }

        // Step 2: For each word, try all possible splits
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();

             for (int cut = 0; cut <= len; cut++) {
                String prefix = word.substring(0, cut);
                String suffix = word.substring(cut);

                // Case 1: If prefix is palindrome, look for reversed(suffix)
                if (isPalindrome(prefix)) {
                    String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                    Integer matchIndex = wordToIndex.get(reversedSuffix);
                    if (matchIndex != null && matchIndex != i) {
                        result.add(Arrays.asList(matchIndex, i));
                    }
                }

                // Case 2: If suffix is palindrome, look for reversed(prefix)
                // cut != len prevents duplicates when prefix is "" and already checked in Case 1
                if (cut != len && isPalindrome(suffix)) {
                    String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                    Integer matchIndex = wordToIndex.get(reversedPrefix);
                    if (matchIndex != null && matchIndex != i) {
                        result.add(Arrays.asList(i, matchIndex));
                    }
                }

                   }
        }

        return result;
    }

    // Helper method to check if a string is a palindrome
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    }
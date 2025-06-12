class Solution {
    
    // Helper function to find frequency of smallest char
    private int getFrequency(String s) {
        char minChar = 'z';
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c < minChar) {
                minChar = c;
                count = 1;
            } else if (c == minChar) {
                count++;
            }
        }
        return count;
    }
    
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wordsFreq = new int[words.length];
        
        // Step 1: Get frequency for each word
        for (int i = 0; i < words.length; i++) {
            wordsFreq[i] = getFrequency(words[i]);
        }
        
        // Step 2: Sort the frequencies to allow binary search
        Arrays.sort(wordsFreq);
        
        int[] result = new int[queries.length];
        
        // Step 3: Process each query
        for (int i = 0; i < queries.length; i++) {
            int freq = getFrequency(queries[i]);
            
            // Binary search: find first index where wordsFreq[index] > freq
            int count = 0;
            int left = 0, right = wordsFreq.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (wordsFreq[mid] <= freq) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            result[i] = wordsFreq.length - left;
        }
        
        return result;
    }
}

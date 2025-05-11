class Solution {
    public int minDeletion(String s, int k) {
 
 
        if (s == null || s.length() == 0) return 0;

        // Step 1: Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: If number of distinct characters is already ≤ k, return 0
        if (freqMap.size() <= k) return 0;

        // Step 3: Add frequencies to a min-heap (or sort)
        List<Integer> freqList = new ArrayList<>(freqMap.values());
        Collections.sort(freqList); // ascending order

        // Step 4: Delete lowest frequency characters until only k remain
        int deletions = 0;
        int toRemove = freqList.size() - k;
        for (int i = 0; i < toRemove; i++) {
            deletions += freqList.get(i);
        }

        return deletions;
    }

    
}

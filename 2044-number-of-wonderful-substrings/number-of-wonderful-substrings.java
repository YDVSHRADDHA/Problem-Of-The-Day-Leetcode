class Solution {
    public long wonderfulSubstrings(String word) {
        int mask = 0;
        long result = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        
        for (char c : word.toCharArray()) {
            // Toggle the bit for this character
            mask ^= (1 << (c - 'a'));
            
            // Add count of substrings with the same mask (all even counts)
            result += freq.getOrDefault(mask, 0);
            
            // Add count of substrings with exactly one odd character
            for (int i = 0; i < 10; i++) {
                int maskWithOneBitFlipped = mask ^ (1 << i);
                result += freq.getOrDefault(maskWithOneBitFlipped, 0);
            }
            
            // Record current mask frequency
            freq.put(mask, freq.getOrDefault(mask, 0) + 1);
        }
        
        return result;
    }
}

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        // Use a HashSet for O(1) lookup
        Set<Character> jewelSet = new HashSet<>();
        
        for (char j : jewels.toCharArray()) {
            jewelSet.add(j);
        }
        
        int count = 0;
        for (char s : stones.toCharArray()) {
            if (jewelSet.contains(s)) {
                count++;
            }
        }
        
        return count;
    }
}

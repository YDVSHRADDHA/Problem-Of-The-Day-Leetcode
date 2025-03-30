class Solution {
    public int partitionString(String s) {
        
        Set<Character> seen = new HashSet<>();
        int partitions = 1; // At least one partition is needed

        for (char c : s.toCharArray()) {
            if (seen.contains(c)) { // If character is repeated, start a new partition
                partitions++;
                seen.clear(); // Reset the set for a new substring
            }
            seen.add(c);
        }
        return partitions;
    
    }
}
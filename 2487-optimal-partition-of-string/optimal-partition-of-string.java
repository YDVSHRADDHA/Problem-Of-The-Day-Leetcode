class Solution {
    public int partitionString(String s) {
        
         boolean[] seen = new boolean[26]; // Use a boolean array instead of a HashSet
        int partitions = 1; // At least one partition is needed

        for (char c : s.toCharArray()) {
            int index = c - 'a'; // Convert char to index (0 to 25)
            if (seen[index]) { // If character already exists, start a new partition
                partitions++;
                Arrays.fill(seen, false); // Reset the array
            }
            seen[index] = true;
        }
        return partitions;
    }
}
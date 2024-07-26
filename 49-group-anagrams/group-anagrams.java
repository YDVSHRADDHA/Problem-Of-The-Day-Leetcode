class Solution {
      public List<List<String>> groupAnagrams(String[] strs) {
        // Map to store sorted character array as key and list of anagrams as value
        Map<String, List<String>> anagramsMap = new HashMap<>();

        // Iterate over each string in the input array
        for (String s : strs) {
            // Convert the string to a character array
            char[] tempArray = s.toCharArray();

            // Sort the character array
            Arrays.sort(tempArray);

            // Convert the sorted character array back to a string
            String sortedString = new String(tempArray);

            // If the sorted string is not in the map, add it with a new list
            if (!anagramsMap.containsKey(sortedString)) {
                anagramsMap.put(sortedString, new ArrayList<>());
            }

            // Add the original string to the list corresponding to the sorted key
            anagramsMap.get(sortedString).add(s);
        }

        // Return the values of the map as a list of lists
        return new ArrayList<>(anagramsMap.values());
    }
}
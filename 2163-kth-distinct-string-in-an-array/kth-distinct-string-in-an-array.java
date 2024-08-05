class Solution {
    public String kthDistinct(String[] arr, int k) {
  
        // Creating a HashMap to store the frequency of each string
        HashMap<String, Integer> map = new HashMap<>();

        // Iterate through the array and populate the map
        for (String str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        // Iterate through the array again to find the k-th distinct string
        for (String str : arr) {
            if (map.get(str) == 1) { // Check if the string is distinct
                k--;
                if (k == 0) {
                    return str; // Return the k-th distinct string
                }
            }
        }

        return ""; // If there is no k-th distinct string, return an empty string
    }
}
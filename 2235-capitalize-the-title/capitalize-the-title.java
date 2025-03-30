class Solution {
    public String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase(); // Convert all letters to lowercase
            
            if (word.length() > 2) {
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1); // Capitalize first letter
            }

            result.append(word);
            if (i < words.length - 1) {
                result.append(" "); // Add space between words
            }
        }

        return result.toString();
    }
}

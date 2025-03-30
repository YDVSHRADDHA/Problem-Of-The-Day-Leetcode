class Solution {
    public String capitalizeTitle(String title) {
  
        char[] chars = title.toCharArray();
        int first = 0;

        for (int i = 0; i < chars.length; i++) {
            // Convert all characters to lowercase
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 32;
            }

            // If we reach a space or end of string, process the word
            if (i == chars.length - 1 || chars[i + 1] == ' ') {
                int length = i - first + 1; // Calculate word length
                if (length > 2 && chars[first] >= 'a' && chars[first] <= 'z') {
                    chars[first] -= 32; // Capitalize first letter
                }
                first = i + 2; // Move pointer to next word's first letter
            }
        }

        return new String(chars);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        // String[] words = title.split(" ");
        // StringBuilder result = new StringBuilder();

        // for (int i = 0; i < words.length; i++) {
        //     String word = words[i].toLowerCase(); // Convert all letters to lowercase
            
        //     if (word.length() > 2) {
        //         word = Character.toUpperCase(word.charAt(0)) + word.substring(1); // Capitalize first letter
        //     }

        //     result.append(word);
        //     if (i < words.length - 1) {
        //         result.append(" "); // Add space between words
        //     }
        // }

        // return result.toString();
    }
}

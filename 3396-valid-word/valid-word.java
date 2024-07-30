class Solution {
    public boolean isValid(String word) {

        // Check if the word has at least 3 characters
        if (word.length() < 3) {
            return false;
        }

        boolean hasVowel = false;
        boolean hasConsonant = false;
        boolean hasDigit = false;
        // boolean isValidCharacters = true;

 for (char c : word.toCharArray()) {
            // Check if the character is a digit or letter
            if (!Character.isLetterOrDigit(c)) {
                //  isValidCharacters
               return false;
                
            }

            // // Check for digits (0-9)
            if (Character.isDigit(c)) {
                 hasDigit = true;
            }
            // Check for vowels
             else if ("aeiouAEIOU".indexOf(c) >= 0) {
                hasVowel= true;
            }

           else {
                hasConsonant = true;
           }


 }
   return hasVowel && hasConsonant; 
    }
}
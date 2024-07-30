class Solution {
    public boolean isPalindrome(String s) {
       // Convert the entire string to lowercase
        s = s.toLowerCase();
        // Convert the string to a character array
        char[] charArray = s.toCharArray();
        
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            // Skip non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(charArray[left])) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(charArray[right])) {
                right--;
            }
            // Compare characters at left and right pointers
            if (charArray[left] != charArray[right]) {
                return false;
            }
            // Move both pointers towards the center
            left++;
            right--;
        }
        
        return true;
    
    }
}
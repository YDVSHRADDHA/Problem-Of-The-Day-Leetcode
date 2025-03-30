class Solution {
    public boolean isPalindrome(String s) {
 
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            left++;
            right--;
        }
        return true;
 















    //    // Convert the entire string to lowercase
    //     s = s.toLowerCase();
    //     // Convert the string to a character array
    //     char[] charArray = s.toCharArray();
        
    //     int left = 0;
    //     int right = charArray.length - 1;

    //     while (left < right) {
    //         // Skip non-alphanumeric characters
    //         while (left < right && !Character.isLetterOrDigit(charArray[left])) {
    //             left++;
    //         }
    //         while (left < right && !Character.isLetterOrDigit(charArray[right])) {
    //             right--;
    //         }
    //         // Compare characters at left and right pointers
    //         if (charArray[left] != charArray[right]) {
    //             return false;
    //         }
    //         // Move both pointers towards the center
    //         left++;
    //         right--;
    //     }
        
    //     return true;
    
    }
}
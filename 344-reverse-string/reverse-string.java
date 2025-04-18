public class Solution {

    // LeetCode-style method: In-place reversal of a char array
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
        System.out.println("In-place reversed: " + new String(s));
    }

    // 1. Using StringBuilder (works on String)
    public void reverseUsingStringBuilder(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println("Reversed with StringBuilder: " + reversed);
    }

    // 2. Using loop (naive approach)
    public void reverseUsingLoop(String str) {
        String rev = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            rev += str.charAt(i);
        }
        System.out.println("Reversed with loop: " + rev);
    }

    // 3. Using Two Pointer method (on String converted to char array)
    public String reverseUsingTwoPointer(String str) {
        char[] arr = str.toCharArray();
        int l = 0, r = arr.length - 1;
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
        String reversed = new String(arr);
        System.out.println("Reversed with Two Pointer: " + reversed);
        return reversed;
    }

    // Main method to test all versions
    public static void main(String[] args) {
        Solution sol = new Solution();
        String input = "hello";

        // Uncomment to test individual methods one by one

        // Method 1: LeetCode in-place reversal (char array)
        // char[] arr = input.toCharArray();
        // sol.reverseString(arr);

        // Method 2: Using StringBuilder
        // sol.reverseUsingStringBuilder(input);

        // Method 3: Using loop
        // sol.reverseUsingLoop(input);

        // Method 4: Two pointer on char array
        // sol.reverseUsingTwoPointer(input);
    }
}

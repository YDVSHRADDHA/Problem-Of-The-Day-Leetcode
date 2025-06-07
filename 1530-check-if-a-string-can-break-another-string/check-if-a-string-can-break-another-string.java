public class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        
        // Sort both strings
        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);
        
        boolean s1BreaksS2 = true;
        boolean s2BreaksS1 = true;
        
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] < arr2[i]) {
                s1BreaksS2 = false;
            }
            if (arr2[i] < arr1[i]) {
                s2BreaksS1 = false;
            }
        }
        
        return s1BreaksS2 || s2BreaksS1;
    }
}

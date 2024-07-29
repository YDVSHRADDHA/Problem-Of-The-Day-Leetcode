class Solution {
    public boolean isAnagram(String s, String t) {

        // If lengths are different, they cannot be anagrams
       if(s.length() != t.length()){
        return false;
       }
             
       // Convert strings to character arrays
       char sArr[] = s.toCharArray();
       char tArr[] = t.toCharArray();

       // Sort the character arrays
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        // Compare the sorted arrays
        return Arrays.equals(sArr, tArr);


    }
}
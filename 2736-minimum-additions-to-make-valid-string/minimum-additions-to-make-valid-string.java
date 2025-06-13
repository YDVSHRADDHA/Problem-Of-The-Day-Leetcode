class Solution {
    public int addMinimum(String word) {
      
    int insertions = 0;
    String pattern = "abc";
    int i = 0; // pointer for word
    int j = 0; // pointer for expected pattern

    while (i < word.length()) {
        if (word.charAt(i) == pattern.charAt(j)) {
            i++;
        } else {
            insertions++; // insert the expected character
        }
        j = (j + 1) % 3; // cycle through "abc"
    }

    // Add remaining chars to complete the final "abc"
    insertions += (3 - j) % 3;

    return insertions;

    }
}
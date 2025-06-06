import java.util.*;

public class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }
    
    private void backtrack(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        
        if (Character.isDigit(chars[index])) {
            // Digit: just continue
            backtrack(chars, index + 1, result);
        } else {
            // Letter: two choices - lowercase or uppercase
            
            // Lowercase
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(chars, index + 1, result);
            
            // Uppercase
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(chars, index + 1, result);
        }
    }
}

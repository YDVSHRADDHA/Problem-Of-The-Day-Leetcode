import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int currentLineLength = 0;
        
        for (String word : words) {
            // Check if adding this word to the current line exceeds maxWidth
            if (currentLineLength + word.length() + currentLine.size() > maxWidth) {
                // If it exceeds, justify the current line and reset
                result.add(justify(currentLine, currentLineLength, maxWidth));
                currentLine.clear();
                currentLineLength = 0;
            }
            // Add the word to the current line
            currentLine.add(word);
            currentLineLength += word.length();
        }
        
        // Handle the last line (left-justified)
        String lastLine = leftJustify(currentLine, maxWidth);
        result.add(lastLine);
        
        return result;
    }
    
    private String justify(List<String> currentLine, int currentLineLength, int maxWidth) {
        // If the current line only has one word, return it left-justified
        if (currentLine.size() == 1) {
            return currentLine.get(0) + " ".repeat(maxWidth - currentLine.get(0).length());
        }
        
        // Calculate total spaces to distribute
        int totalSpaces = maxWidth - currentLineLength;
        int spacesBetweenWords = totalSpaces / (currentLine.size() - 1);
        int extraSpaces = totalSpaces % (currentLine.size() - 1);
        
        StringBuilder line = new StringBuilder();
        
        // Add words with spaces between them
        for (int i = 0; i < currentLine.size(); i++) {
            line.append(currentLine.get(i));
            if (i < currentLine.size() - 1) {
                int spacesToAdd = spacesBetweenWords + (i < extraSpaces ? 1 : 0);
                line.append(" ".repeat(spacesToAdd));
            }
        }
        
        return line.toString();
    }
    
    private String leftJustify(List<String> currentLine, int maxWidth) {
        StringBuilder line = new StringBuilder();
        for (String word : currentLine) {
            line.append(word).append(" ");
        }
        // Remove the last space and add spaces to the end of the line
        line.deleteCharAt(line.length() - 1);
        line.append(" ".repeat(maxWidth - line.length()));
        return line.toString();
    }
}

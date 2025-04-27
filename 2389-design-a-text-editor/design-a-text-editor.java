class TextEditor {
    // A StringBuilder to store the text in the editor.
    StringBuilder res;
    
    // Position of the cursor in the text.
    int pos = 0;
    
    // Constructor initializes the StringBuilder and cursor position.
    public TextEditor() {
        res = new StringBuilder();
    }
    
    // Method to add text at the current cursor position.
    public void addText(String text) {
        // Insert the given text at the current position.
        res.insert(pos, text);
        
        // Move the cursor to the right of the newly inserted text.
        pos += text.length();
    }
    
    // Method to delete 'k' characters to the left of the cursor.
    public int deleteText(int k) {
        // Save the current cursor position as a temporary variable.
        int tmp = pos;
        
        // Move the cursor 'k' steps left.
        pos -= k;
        
        // Ensure the cursor position doesn't go below 0.
        if (pos < 0) pos = 0;
        
        // Delete characters from the text.
        res.delete(pos, tmp);
        
        // Return the number of characters actually deleted.
        return tmp - pos;
    }
    
    // Method to move the cursor left by 'k' positions and return the last up to 10 characters to the left.
    public String cursorLeft(int k) {
        // Move the cursor left by 'k' positions.
        pos -= k;
        
        // Ensure the cursor doesn't move before the beginning of the text.
        if (pos < 0) pos = 0;
        
        // If there are fewer than 10 characters to the left, return all available characters.
        if (pos < 10) return res.substring(0, pos);
        
        // Otherwise, return the last 10 characters before the cursor.
        return res.substring(pos - 10, pos);
    }
    
    // Method to move the cursor right by 'k' positions and return the last up to 10 characters to the left.
    public String cursorRight(int k) {
        // Move the cursor right by 'k' positions.
        pos += k;
        
        // Ensure the cursor doesn't go past the end of the text.
        if (pos > res.length()) pos = res.length();
        
        // If there are fewer than 10 characters to the left, return all available characters.
        if (pos < 10) return res.substring(0, pos);
        
        // Otherwise, return the last 10 characters before the cursor.
        return res.substring(pos - 10, pos);
    }
}

class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) return s;  // base case
        
        List<String> parts = new ArrayList<>();
        int count = 0, start = 0;
        
        // Divide string into special substrings
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
            else count--;
            
            if (count == 0) {
                // Recursively solve for inner string
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                parts.add("1" + inner + "0");
                start = i + 1;
            }
        }
        
        // Sort parts in descending order for lexicographically largest string
        Collections.sort(parts, Collections.reverseOrder());
        
        // Combine all parts
        StringBuilder res = new StringBuilder();
        for (String part : parts)
            res.append(part);
        
        return res.toString();
    }
}

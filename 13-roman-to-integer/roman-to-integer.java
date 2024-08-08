class Solution {
    public int romanToInt(String s) {
        // Mapping of Roman symbols to their integer values
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;

        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            // Get the value of the current symbol
            int currentValue = romanMap.get(s.charAt(i));
            // Get the value of the next symbol, if it exists
            int nextValue = (i + 1 < s.length()) ? romanMap.get(s.charAt(i + 1)) : 0;

            // If the next value is larger, subtract the current value
            if (currentValue < nextValue) {
                total -= currentValue;
            } else {
                // Otherwise, add the current value
                total += currentValue;
            }
        }

        return total;
    }
}

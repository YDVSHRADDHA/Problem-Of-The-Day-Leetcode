public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) result.append("-");

        // Convert to long to handle overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Integer part
        result.append(num / den);
        num %= den;

        if (num == 0) return result.toString();

        // Fractional part
        result.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (num != 0) {
            if (map.containsKey(num)) {
                result.insert(map.get(num), "(");
                result.append(")");
                break;
            }
            map.put(num, result.length());
            num *= 10;
            result.append(num / den);
            num %= den;
        }

        return result.toString();
    }
}

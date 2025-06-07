public class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        for (int i = 1; i <= n / 2; i++) {
            for (int j = i + 1; j < n; j++) {
                String num1 = num.substring(0, i);
                String num2 = num.substring(i, j);

                // Skip if num1 or num2 has leading zero (but not "0" itself)
                if ((num1.length() > 1 && num1.charAt(0) == '0') ||
                    (num2.length() > 1 && num2.charAt(0) == '0')) {
                    continue;
                }

                if (isValid(num1, num2, num.substring(j))) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValid(String num1, String num2, String remaining) {
        while (!remaining.isEmpty()) {
            String sum = addStrings(num1, num2);

            if (!remaining.startsWith(sum)) return false;

            remaining = remaining.substring(sum.length());
            num1 = num2;
            num2 = sum;
        }

        return true;
    }

    // Helper to add two numeric strings
    private String addStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int digitB = j >= 0 ? b.charAt(j--) - '0' : 0;

            int sum = digitA + digitB + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }
}

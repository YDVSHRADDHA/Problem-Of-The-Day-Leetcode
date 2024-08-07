class Solution {

    // Create Word Arrays:
    private static final String[] ONES = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    // Convert Number Less Than 1000:
    private static String convertLessThanOneThousand(int num) {
        String result = "";

        if (num % 100 < 20) {
            if (num % 100 < 10) {
                result = ONES[num % 100];
            } else {
                result = TEENS[num % 100 - 10];
            }
            num /= 100;
        } else {
            result = TENS[num % 100 / 10];
            result += (num % 10 != 0 ? " " + ONES[num % 10] : "");
            num /= 100;
        }

        if (num == 0) return result;
        return ONES[num] + " Hundred" + (result.isEmpty() ? "" : " " + result);
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String words = "";
        int thousandCounter = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                words = convertLessThanOneThousand(num % 1000) + " " + THOUSANDS[thousandCounter] + " " + words;
            }
            num /= 1000;
            thousandCounter++;
        }

        return words.trim();
    }

}
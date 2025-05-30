class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0"; 
        
        int len1 = num1.length(), len2 = num2.length();
        int[] result = new int[len1 + len2]; // Maximum possible length
        
        // Multiply each digit
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int sum = mul + result[i + j + 1]; 
                
                result[i + j + 1] = sum % 10; 
                result[i + j] += sum / 10; 
            }
        }
        
        // Convert to string and skip leading zeros
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (!(sb.length() == 0 && num == 0)) { // Skip leading zeroes
                sb.append(num);
            }
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

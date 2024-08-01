class Solution {
    public int countSeniors(String[] details) {
          int count = 0;
        for (String detail : details) {
            // Extract the substring for age and convert to integer
            int age = Integer.parseInt(detail.substring(11, 13));
            // Check if the age is strictly more than 60
            if (age > 60) {
                count += 1;
            }
        }
        return count;
    }
}
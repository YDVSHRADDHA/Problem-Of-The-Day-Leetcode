public class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        
        // Step 1: Give each child at least one candy
        Arrays.fill(candies, 1);
        
        // Step 2: Left to Right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        // Step 3: Right to Left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        
        // Step 4: Sum all candies
        int totalCandies = 0;
        for (int c : candies) {
            totalCandies += c;
        }
        
        return totalCandies;
    }
}

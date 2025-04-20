import java.util.*;

public class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        
        // Create a list of stones with their combined value, Alice's value, and Bob's value
        List<int[]> stones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stones.add(new int[]{aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]});
        }
        
        // Sort the stones based on the combined value (descending order)
        Collections.sort(stones, (a, b) -> Integer.compare(b[0], a[0]));
        
        int aliceScore = 0, bobScore = 0;
        
        // Alternate picking stones
        for (int i = 0; i < n; i++) {
            int[] stone = stones.get(i);
            if (i % 2 == 0) {  // Alice's turn
                aliceScore += stone[1];
            } else {  // Bob's turn
                bobScore += stone[2];
            }
        }
        
        // Determine the result
        if (aliceScore > bobScore) return 1;
        if (bobScore > aliceScore) return -1;
        return 0;  // Draw
    }
}

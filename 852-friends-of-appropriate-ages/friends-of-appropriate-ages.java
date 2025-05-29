class Solution {
    public int numFriendRequests(int[] ages) {
  
        int[] count = new int[121]; // Age range is 1 to 120
        for (int age : ages) {
            count[age]++;
        }

        int totalRequests = 0;
        for (int ageA = 15; ageA <= 120; ageA++) {
            if (count[ageA] == 0) continue;
            for (int ageB = 15; ageB <= 120; ageB++) {
                if (count[ageB] == 0) continue;

                if (ageB <= 0.5 * ageA + 7) continue;
                if (ageB > ageA) continue;
                if (ageB > 100 && ageA < 100) continue;

                totalRequests += count[ageA] * count[ageB];
                if (ageA == ageB) totalRequests -= count[ageA]; // remove self requests
            }
        }

        return totalRequests;
    
}

}
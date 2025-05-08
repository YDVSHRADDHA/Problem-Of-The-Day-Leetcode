class Solution {
    public int minSwapsCouples(int[] row) {
 
        int n = row.length;
        int[] pos = new int[n];  // Map person → position

        // Build position map
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i += 2) {
            int first = row[i];
            int partner = first ^ 1;

            if (row[i + 1] != partner) {
                swaps++;

                // Swap with correct partner
                int partnerIndex = pos[partner];
                int second = row[i + 1];

                // Update array and position map
                row[partnerIndex] = second;
                row[i + 1] = partner;

                pos[second] = partnerIndex;
                pos[partner] = i + 1;
            }
        }

        return swaps;
    }
}

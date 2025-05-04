class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] result = new int[queries.length]; // To store count for each circle

        for (int i = 0; i < queries.length; i++) {
            int centerX = queries[i][0];
            int centerY = queries[i][1];
            int radius = queries[i][2];
            int radiusSquared = radius * radius; // Avoid square roots

            int count = 0;

            for (int[] point : points) {
                int dx = point[0] - centerX;
                int dy = point[1] - centerY;

                // Distance squared <= radius squared → inside circle
                if (dx * dx + dy * dy <= radiusSquared) {
                    count++;
                }
            }

            result[i] = count; // Save count for this circle
        }

        return result;
    }
}

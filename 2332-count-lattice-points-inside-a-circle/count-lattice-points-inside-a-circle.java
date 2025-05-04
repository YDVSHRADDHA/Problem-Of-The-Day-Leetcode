class Solution {
    public int countLatticePoints(int[][] circles) {
        Set<String> seen = new HashSet<>(); // To store unique lattice points

        for (int[] circle : circles) {
            int x = circle[0];
            int y = circle[1];
            int r = circle[2];

            for (int i = x - r; i <= x + r; i++) {
                for (int j = y - r; j <= y + r; j++) {
                    int dx = i - x;
                    int dy = j - y;
                    if (dx * dx + dy * dy <= r * r) {
                        seen.add(i + "," + j); // Store as string "x,y"
                    }
                }
            }
        }

        return seen.size();
    }
}

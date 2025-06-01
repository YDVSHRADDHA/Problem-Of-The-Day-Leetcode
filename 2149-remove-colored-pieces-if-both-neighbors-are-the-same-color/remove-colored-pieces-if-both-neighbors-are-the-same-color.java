class Solution {
    public boolean winnerOfGame(String colors) {
        int aliceMoves = 0, bobMoves = 0;
        int count = 1;
        
        for (int i = 1; i < colors.length(); i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                count++;
            } else {
                if (colors.charAt(i - 1) == 'A' && count >= 3) {
                    aliceMoves += (count - 2);
                } else if (colors.charAt(i - 1) == 'B' && count >= 3) {
                    bobMoves += (count - 2);
                }
                count = 1;
            }
        }
        
        // Check last segment
        if (colors.charAt(colors.length() - 1) == 'A' && count >= 3) {
            aliceMoves += (count - 2);
        } else if (colors.charAt(colors.length() - 1) == 'B' && count >= 3) {
            bobMoves += (count - 2);
        }
        
        return aliceMoves > bobMoves;
    }
}

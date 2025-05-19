public class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Sets to track digits in rows, columns, and 3x3 boxes
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        // Initialize each set
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Traverse each cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];

                // Skip empty cells
                if (val == '.') continue;

                // Box index formula
                int boxIndex = (i / 3) * 3 + (j / 3);

                // Check for duplicates
                if (rows[i].contains(val) || cols[j].contains(val) || boxes[boxIndex].contains(val)) {
                    return false;
                }

                // Add value to sets
                rows[i].add(val);
                cols[j].add(val);
                boxes[boxIndex].add(val);
            }
        }

        return true; // No duplicates found
    }
}

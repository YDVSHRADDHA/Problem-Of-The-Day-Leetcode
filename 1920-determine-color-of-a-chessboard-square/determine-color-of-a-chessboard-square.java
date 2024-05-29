class Solution {
    public boolean squareIsWhite(String coordinates) {

        char columnLetter = coordinates.charAt(0); // 'a'
        int row = Character.getNumericValue(coordinates.charAt(1)); // '1' to 1

        // Determine if the sum is odd or even
        return (columnLetter + row) % 2 == 1;

    }
}
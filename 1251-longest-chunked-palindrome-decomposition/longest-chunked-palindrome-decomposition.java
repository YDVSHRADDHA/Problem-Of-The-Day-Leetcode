public class Solution {
    public int longestDecomposition(String text) {
        int left = 0, right = text.length() - 1;
        int count = 0;

        while (left <= right) {
            boolean found = false;

            // Try increasing chunk size from left and right to find matching chunks
            for (int size = 1; left + size - 1 < right - size + 1; size++) {
                String leftChunk = text.substring(left, left + size);
                String rightChunk = text.substring(right - size + 1, right + 1);

                if (leftChunk.equals(rightChunk)) {
                    count += (left + size - 1 == right - size + 1) ? 1 : 2;
                    left += size;
                    right -= size;
                    found = true;
                    break;
                }
            }

            // If no matching chunk found, the remaining string is one chunk
            if (!found) {
                count++;
                break;
            }
        }
        return count;
    }
}

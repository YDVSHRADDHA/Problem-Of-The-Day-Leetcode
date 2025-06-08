class Solution {
    public int lengthLongestPath(String input) {
        String[] lines = input.split("\n");
        Map<Integer, Integer> depthLengthMap = new HashMap<>();
        int maxLen = 0;

        for (String line : lines) {
            int depth = line.lastIndexOf("\t") + 1;
            String name = line.substring(depth);

            // If it's a file
            if (name.contains(".")) {
                int fileLen = depthLengthMap.getOrDefault(depth - 1, 0) + name.length();
                maxLen = Math.max(maxLen, fileLen);
            } else {
                // It's a directory, so add 1 for the "/"
                int currLen = depthLengthMap.getOrDefault(depth - 1, 0) + name.length() + 1;
                depthLengthMap.put(depth, currLen);
            }
        }

        return maxLen;
    }
}

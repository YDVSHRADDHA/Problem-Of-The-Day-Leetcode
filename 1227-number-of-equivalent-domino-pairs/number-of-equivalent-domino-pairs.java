class Solution {
   public int numEquivDominoPairs(int[][] dominoes) {
    Map<String, Integer> map = new HashMap<>();
    int count = 0;

    for (int[] d : dominoes) {
        int a = Math.min(d[0], d[1]);
        int b = Math.max(d[0], d[1]);
        String key = a + "," + b;

        int freq = map.getOrDefault(key, 0);
        count += freq; // each time we see the same domino, it forms 'freq' new pairs
        map.put(key, freq + 1);
    }

    return count;
}

}
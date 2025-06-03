import java.util.*;

class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    private Random rand = new Random();
    private int size;

    public Solution(int n, int[] blacklist) {
        size = n - blacklist.length;
        Set<Integer> blackSet = new HashSet<>();
        
        for (int b : blacklist)
            blackSet.add(b);

        int last = n - 1;
        for (int b : blacklist) {
            if (b < size) {
                // Find next non-blacklisted number >= size
                while (blackSet.contains(last)) {
                    last--;
                }
                map.put(b, last);
                last--;
            }
        }
    }

    public int pick() {
        int num = rand.nextInt(size);
        return map.getOrDefault(num, num);
    }
}

public class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) return 0;

        int s1Count = 0, s2Count = 0, index = 0;
        Map<Integer, int[]> map = new HashMap<>();

        while (s1Count < n1) {
            for (char ch : s1.toCharArray()) {
                if (ch == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        s2Count++;
                        index = 0;
                    }
                }
            }

            s1Count++;

            if (map.containsKey(index)) {
                int[] previous = map.get(index);
                int preS1Count = previous[0];
                int preS2Count = previous[1];

                int cycleLenS1 = s1Count - preS1Count;
                int cycleLenS2 = s2Count - preS2Count;

                int remainingCycles = (n1 - s1Count) / cycleLenS1;

                s1Count += remainingCycles * cycleLenS1;
                s2Count += remainingCycles * cycleLenS2;
            } else {
                map.put(index, new int[]{s1Count, s2Count});
            }
        }

        return s2Count / n2;
    }
}

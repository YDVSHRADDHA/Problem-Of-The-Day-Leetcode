import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String reorganizeString(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Max heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(freq.entrySet());

        StringBuilder result = new StringBuilder();

        // Previous character and count to hold for the next iteration
        Map.Entry<Character, Integer> prev = null;

        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> current = maxHeap.poll();
            result.append(current.getKey());

            // Decrement count
            current.setValue(current.getValue() - 1);

            // Add previous back if still has count left
            if (prev != null && prev.getValue() > 0) {
                maxHeap.offer(prev);
            }

            // Current becomes previous for next iteration
            prev = current;
        }

        // If the reorganized string length != original length, return ""
        return result.length() == s.length() ? result.toString() : "";
    }
}

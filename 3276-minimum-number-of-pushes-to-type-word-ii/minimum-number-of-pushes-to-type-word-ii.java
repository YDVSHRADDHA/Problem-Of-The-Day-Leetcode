class Solution {
    public int minimumPushes(String word) {
      
        // Step 1: Count the frequency of each letter using HashMap
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        // Step 2: Create a priority queue (max-heap) to manage frequencies
        PriorityQueue<Integer> frequencyQueue = new PriorityQueue<>(Collections.reverseOrder());
        frequencyQueue.addAll(frequencyMap.values());
        
        // Step 3: Calculate the total number of presses needed
        int totalPushes = 0;
        int index = 0;
        while (!frequencyQueue.isEmpty()) {
            int frequency = frequencyQueue.poll();
            // (1 + (index / 8)) gives the press count for the current character
            totalPushes += (1 + (index / 8)) * frequency;
            index++;
        }

        return totalPushes;
    }
}
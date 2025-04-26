import java.util.*;

class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        if (responses == null || responses.isEmpty()) {
            return "";
        }

        HashMap<String, Integer> freqMap = new HashMap<>();

        for (List<String> dayResponses : responses) {
            Set<String> uniqueResponses = new HashSet<>(dayResponses); // remove duplicates in a day
            for (String response : uniqueResponses) {
                freqMap.put(response, freqMap.getOrDefault(response, 0) + 1);
            }
        }

        String result = "";
        int maxFreq = 0;

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();

            if (freq > maxFreq) {
                maxFreq = freq;
                result = word;
            } else if (freq == maxFreq) {
                if (word.compareTo(result) < 0) { // lex smaller
                    result = word;
                }
            }
        }

        return result;
    }
}

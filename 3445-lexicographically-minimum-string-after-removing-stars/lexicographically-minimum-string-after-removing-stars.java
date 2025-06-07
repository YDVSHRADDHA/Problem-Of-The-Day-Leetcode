import java.util.*;

class Solution {
    public String clearStars(String s) {
        char[] arr = s.toCharArray();
        Deque<Integer>[] cnt = new Deque[26];

        for (int i = 0; i < 26; i++) {
            cnt[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '*') {
                cnt[arr[i] - 'a'].push(i);
            } else {
                // Remove the smallest character to the left
                for (int j = 0; j < 26; j++) {
                    while (!cnt[j].isEmpty() && cnt[j].peek() > i) {
                        cnt[j].pop(); // Ignore if not to the left
                    }
                    if (!cnt[j].isEmpty()) {
                        int indexToRemove = cnt[j].pop();
                        arr[indexToRemove] = '*'; // mark deleted
                        break;
                    }
                }
                arr[i] = '*'; // remove this star too
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != '*') {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}

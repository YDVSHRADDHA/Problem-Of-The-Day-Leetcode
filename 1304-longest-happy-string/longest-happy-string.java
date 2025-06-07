class Solution {
    public String longestDiverseString(int a, int b, int c) {
  
    // Max-heap by count
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[0] - x[0]);
    if (a > 0) maxHeap.offer(new int[]{a, 'a'});
    if (b > 0) maxHeap.offer(new int[]{b, 'b'});
    if (c > 0) maxHeap.offer(new int[]{c, 'c'});
    
    StringBuilder sb = new StringBuilder();
    
    while (!maxHeap.isEmpty()) {
        int[] first = maxHeap.poll();
        
        int length = sb.length();
        // Check if adding first character causes 3 in a row
        if (length >= 2 && sb.charAt(length - 1) == first[1] && sb.charAt(length - 2) == first[1]) {
            if (maxHeap.isEmpty()) break; // no other character to use
            
            int[] second = maxHeap.poll();
            sb.append((char) second[1]);
            second[0]--;
            if (second[0] > 0) maxHeap.offer(second);
            maxHeap.offer(first); // put first back for later
        } else {
            sb.append((char) first[1]);
            first[0]--;
            if (first[0] > 0) maxHeap.offer(first);
        }
    }
    
    return sb.toString();
}
 
}
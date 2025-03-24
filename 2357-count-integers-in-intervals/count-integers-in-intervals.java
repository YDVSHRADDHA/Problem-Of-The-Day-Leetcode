class CountIntervals {

     private TreeMap<Integer, Integer> intervals; // Stores intervals in sorted order
    private int totalCount; // Stores total count of unique numbers covered


    public CountIntervals() {
         intervals = new TreeMap<>();
        totalCount = 0;
    }
    
    public void add(int left, int right) {
        // Merging overlapping intervals
        Integer start = intervals.floorKey(right); // Find largest key <= right

        while (start != null && intervals.get(start) >= left - 1) {
            // Merge overlapping intervals
            left = Math.min(left, start);
            right = Math.max(right, intervals.get(start));
            totalCount -= (intervals.get(start) - start + 1); // Remove old interval count
            intervals.remove(start);
            start = intervals.floorKey(right);
        }

        // Add merged interval
        intervals.put(left, right);
        totalCount += (right - left + 1); // Update total count
    }
    
    public int count() {
         return totalCount;
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        // PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num : nums){
            pq.add(num*num);
        }

        int arr[] = new int[nums.length];
        int i=0;
        while(!pq.isEmpty()){
            arr[i++] = pq.poll();
        }
        return arr;
    }
}
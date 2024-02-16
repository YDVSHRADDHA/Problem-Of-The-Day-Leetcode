class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
       Map<Integer,Integer> frequencyMap = new HashMap<>();

    // Count the frequency of each element...
       for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

    // Sort the frequencies in ascending order.
     int[] frequencies = new int[frequencyMap.size()];
        int i = 0;
        for (int freq : frequencyMap.values()) {
            frequencies[i++] = freq;
        }
        
    Arrays.sort(frequencies);
        int cnt =0;
    // Iterate through the sorted frequencies and substract from k.
    for(int freq : frequencies){
        if(k>=freq){
            k-=freq;
        }
        else{
           cnt++;
        }
    }
     return cnt;
    }
   
}
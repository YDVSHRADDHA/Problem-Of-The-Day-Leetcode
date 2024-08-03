

class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        
//  Intuition :  If the two arrays contain the same elements with the same frequencies, their sorted versions will be identical.  <<< Sorting!
        
// -----------------------------------------------------------------------------------------------------------------------
   
        // Approach Using Sorting.
         
        // Step 1: Sort both arrays
        Arrays.sort(target);
        Arrays.sort(arr);

        // Step 2: Compare the sorted arrays
        return Arrays.equals(target, arr);
        

 
         //  Intuition : both arrays must contain the same elements with the same frequencies. <<< HashMap!
// -----------------------------------------------------------------------------------------------------------------------
        // Approach Using HashMap.



         // Step 1: Check if lengths are different
        // if (target.length != arr.length) {
        //     return false;
        // }

         // Step 2: Create HashMaps to count frequencies
        // HashMap<Integer,Integer> mapT = new HashMap<>();
        // HashMap<Integer,Integer> mapA = new HashMap<>();

        // for(int t : target){
        //     mapT.put(t, mapT.getOrDefault(t,0)+1);
        // }

        // for(int a : arr){
        //     mapA.put(a, mapA.getOrDefault(a,0)+1);
        // }

        // Step 3: Compare the HashMaps
        // return mapT.equals(mapA);
    }
   
}
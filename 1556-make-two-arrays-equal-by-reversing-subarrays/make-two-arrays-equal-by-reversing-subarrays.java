//  Intuition : both arrays must contain the same elements with the same frequencies. <<< HashMap!

class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        HashMap<Integer,Integer> mapT = new HashMap<>();
        HashMap<Integer,Integer> mapA = new HashMap<>();

        for(int t : target){
            mapT.put(t, mapT.getOrDefault(t,0)+1);
        }

        for(int a : arr){
            mapA.put(a, mapA.getOrDefault(a,0)+1);
        }

        return mapT.equals(mapA);
    }
   
}
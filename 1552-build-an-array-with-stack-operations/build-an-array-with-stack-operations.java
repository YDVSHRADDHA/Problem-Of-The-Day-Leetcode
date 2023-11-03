class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();

        int targetIndex  = 0;
        
        for(int i = 1; i <= n && targetIndex < target.length;  i++){
            if( i == target[targetIndex]){
                operations.add("Push");
                targetIndex++;
            }
            else{
                operations.add("Push");
                operations.add("Pop");
            }
        }

        return operations;

    }
}
class Solution {
    public String reverseWords(String s) {

        String[] arr = s.split(" ");
        s= "";

        for(int i=arr.length-1;i>=0;i--){
            if(arr[i].length()!=0){
                s = s.concat(arr[i] + " ");
            }
        }

        return s.trim();
    }
}
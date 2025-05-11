 class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0) {
                return true;
            }
        }
        return false;
    }
}

// class Solution {
//     public boolean threeConsecutiveOdds(int[] arr) {
//         int cnt =0;
//         for(int i=0; i<arr.length;i++){
//             if(arr[i] % 2 != 0){
//              cnt++;
//               if(cnt>=3){
//             return true;
//         }

//             }
//             else{
//                 cnt =0;
//             }
//         }
//         if(cnt>=3){
//             return true;
//         }

//         return false;
//     }
// }
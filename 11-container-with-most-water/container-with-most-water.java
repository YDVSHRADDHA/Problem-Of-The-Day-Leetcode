class Solution {
    public int maxArea(int[] height) {
       int i = 0; 
       int j = height.length - 1;
       int maxWater = 0;
        while(i<j){ 
               int h = Math.min(height[i],height[j]);
               int calArea = h*(j-i);
               maxWater = Math.max(maxWater, calArea);

               while(height[i]<= h && i<j){
                  i++;
               }
               while(height[j] <= h && i<j){
                  j--;
               }
           }
        
       
          return maxWater;
    }
}
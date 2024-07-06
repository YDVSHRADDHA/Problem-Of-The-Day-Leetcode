class Solution {
    public int passThePillow(int n, int time) {
        int ans = 1; // starting person
        int direction = 1; // 1 means forward, -1 means backward

        while(time > 0) {
            ans += direction;
            if(ans == n || ans == 1) {
                direction = -direction; // reverse direction when reaching the ends
            }
            time--;
        }
        return ans;
    }
}

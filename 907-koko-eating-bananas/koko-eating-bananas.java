class Solution {
    public int minEatingSpeed(int[] piles, int h) {
 
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canEatAll(piles, h, mid)) {
                high = mid; // try slower speed
            } else {
                low = mid + 1; // need faster speed
            }
        }

        return low;
    }

    private boolean canEatAll(int[] piles, int h, int k) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + k - 1) / k; // ceil(pile / k)
        }
        return hours <= h;
    }
}

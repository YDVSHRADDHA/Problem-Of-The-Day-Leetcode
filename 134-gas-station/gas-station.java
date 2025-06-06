class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currTank = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int gain = gas[i] - cost[i];
            totalTank += gain;
            currTank += gain;

            if (currTank < 0) {
                // Cannot start from current segment
                startStation = i + 1;
                currTank = 0;
            }
        }

        return totalTank >= 0 ? startStation : -1;
    }
}

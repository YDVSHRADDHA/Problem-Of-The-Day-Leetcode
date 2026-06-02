class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {

                // Land -> Water
                int landFinish = landStartTime[i] + landDuration[i];
                int total1 = Math.max(landFinish, waterStartTime[j]) + waterDuration[j];

                // Water -> Land
                int waterFinish = waterStartTime[j] + waterDuration[j];
                int total2 = Math.max(waterFinish, landStartTime[i]) + landDuration[i];

                ans = Math.min(ans, Math.min(total1, total2));
            }
        }

        return ans;
    }
}
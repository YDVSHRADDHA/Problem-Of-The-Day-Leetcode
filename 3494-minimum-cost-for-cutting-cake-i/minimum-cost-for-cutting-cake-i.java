class Solution {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
  
        Integer[] h = Arrays.stream(horizontalCut).boxed().toArray(Integer[]::new);
        Integer[] v = Arrays.stream(verticalCut).boxed().toArray(Integer[]::new);

        Arrays.sort(h, Collections.reverseOrder());
        Arrays.sort(v, Collections.reverseOrder());

        int hIdx = 0, vIdx = 0;
        int hPieces = 1, vPieces = 1;
        int cost = 0;

        while (hIdx < h.length && vIdx < v.length) {
            if (h[hIdx] >= v[vIdx]) {
                cost += h[hIdx++] * vPieces;
                hPieces++;
            } else {
                cost += v[vIdx++] * hPieces;
                vPieces++;
            }
        }

        // Remaining horizontal cuts
        while (hIdx < h.length) {
            cost += h[hIdx++] * vPieces;
        }

        // Remaining vertical cuts
        while (vIdx < v.length) {
            cost += v[vIdx++] * hPieces;
        }

        return cost;
    }
}

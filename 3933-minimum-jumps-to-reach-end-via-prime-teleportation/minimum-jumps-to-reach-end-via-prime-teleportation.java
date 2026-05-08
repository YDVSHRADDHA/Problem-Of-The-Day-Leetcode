class Solution {

    private static final int MAX = 1_000_001;

    private static int[] spf = new int[MAX];
    private static boolean sieveBuilt = false;

    // Build SPF only once
    private static void buildSPF() {

        if (sieveBuilt) return;

        for (int i = 0; i < MAX; i++) {
            spf[i] = i;
        }

        for (int i = 2; i * i < MAX; i++) {

            if (spf[i] == i) {

                for (int j = i * i; j < MAX; j += i) {

                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        sieveBuilt = true;
    }

    private boolean isPrime(int x) {
        return x >= 2 && spf[x] == x;
    }

    // Unique prime factors
    private List<Integer> getPrimeFactors(int x) {

        List<Integer> factors = new ArrayList<>();

        while (x > 1) {

            int p = spf[x];
            factors.add(p);

            while (x % p == 0) {
                x /= p;
            }
        }

        return factors;
    }

    public int minJumps(int[] nums) {

        buildSPF();

        int n = nums.length;

        if (n == 1) return 0;

        // factor -> indices
        Map<Integer, List<Integer>> factorMap = new HashMap<>();

        for (int i = 0; i < n; i++) {

            List<Integer> factors = getPrimeFactors(nums[i]);

            for (int p : factors) {

                factorMap
                    .computeIfAbsent(p, k -> new ArrayList<>())
                    .add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int jumps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int i = q.poll();

                if (i == n - 1) {
                    return jumps;
                }

                // left
                if (i - 1 >= 0 && !visited[i - 1]) {

                    visited[i - 1] = true;
                    q.offer(i - 1);
                }

                // right
                if (i + 1 < n && !visited[i + 1]) {

                    visited[i + 1] = true;
                    q.offer(i + 1);
                }

                // teleport
                if (isPrime(nums[i])) {

                    int p = nums[i];

                    List<Integer> next =
                        factorMap.get(p);

                    if (next != null) {

                        for (int idx : next) {

                            if (!visited[idx]) {

                                visited[idx] = true;
                                q.offer(idx);
                            }
                        }

                        // CRITICAL OPTIMIZATION
                        factorMap.remove(p);
                    }
                }
            }

            jumps++;
        }

        return -1;
    }
}
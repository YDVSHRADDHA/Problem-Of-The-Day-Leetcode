import java.util.*;

class Solution {
    // Helper function to compute all primes up to n using Sieve of Eratosthenes
    private List<Integer> sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        // Implementing the sieve
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // Collect all primes up to n
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Get all primes up to n
        List<Integer> primes = sieveOfEratosthenes(n);
        Set<Integer> primeSet = new HashSet<>(primes); // Set for quick lookups
        
        // Try all pairs (x, y) where x + y = n
        for (int x : primes) {
            int y = n - x;
            // Ensure x <= y to avoid duplicate pairs like (3,7) and (7,3)
            if (y >= x && primeSet.contains(y)) {
                result.add(Arrays.asList(x, y));
            }
        }
        
        return result;
    }
}

class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;  // There are no prime numbers less than 2
        }

        // Initialize an array of boolean values to track prime numbers
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;  // Initially assume all numbers are prime
        }

        // Implementing Sieve of Eratosthenes
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {  // If i is a prime
                // Mark all multiples of i as non-prime
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Count the number of primes
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}

// class Solution {
//     public int countPrimes(int n) {
//         int cnt = 0;
//         if (n <= 1) return 0; // Handle edge case

//         for (int i = 2; i < n; i++) { // Iterate through numbers < n
//             if (isPrime(i)) { 
//                 cnt++;
//             }
//         }

//         return cnt;
//     }

//     private boolean isPrime(int num) {
//         if (num < 2) return false; // 0 and 1 are not prime
//         for (int i = 2; i * i <= num; i++) { // Check divisibility
//             if (num % i == 0) return false;
//         }
//         return true;
//     }
// }

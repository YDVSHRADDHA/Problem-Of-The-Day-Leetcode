class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefixXor = new int[n];  
        prefixXor[0] = arr[0]; 
        
        // Compute prefix XOR
        for (int i = 1; i < n; i++) {
            prefixXor[i] = prefixXor[i - 1] ^ arr[i];
        }

        int q = queries.length;
        int[] result = new int[q];
        
        // Answer each query in O(1)
        for (int i = 0; i < q; i++) {
            int left = queries[i][0], right = queries[i][1];
            result[i] = (left == 0) ? prefixXor[right] : (prefixXor[right] ^ prefixXor[left - 1]);
        }
        
        return result;
    }
}

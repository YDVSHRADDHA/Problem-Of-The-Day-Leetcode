class Solution {
      public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int k, int remain, int start) {
        if (tempList.size() > k) return;  // if the combination length exceeds k, return
        if (remain == 0 && tempList.size() == k) {
            result.add(new ArrayList<>(tempList));  // if valid combination found, add it to result
            return;
        }
        for (int i = start; i <= 9; i++) {  // iterate through numbers from start to 9
            tempList.add(i);  // add number to combination
            backtrack(result, tempList, k, remain - i, i + 1);  // recurse with reduced sum and next number
            tempList.remove(tempList.size() - 1);  // backtrack
        }
    }
     
    }
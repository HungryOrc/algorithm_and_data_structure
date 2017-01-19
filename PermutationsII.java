/* Given a list of numbers with duplicate number in it. Find all unique permutations.

Example:
For numbers [1,2,2] the unique permutations are:
[
  [1,2,2],
  [2,1,2],
  [2,2,1]
] */

public class Solution {
    
    // Recursion: 我的土办法。速度较慢
    public List<List<Integer>> permuteUnique(int[] nums) {
        
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        
        if (nums == null) {
            return results;
        }
        else if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        Arrays.sort(nums);
        // construct the original records of each integer's occurrence times in nums
        HashMap<Integer,Integer> originalOccurrences = new HashMap<>();
        for (int n : nums) {
            originalOccurrences.put(n, originalOccurrences.getOrDefault(n, 0) + 1);
        }
        
        findPermutations(nums, originalOccurrences, new HashMap<Integer,Integer>(), new ArrayList<Integer>(), results);
        return results;
    } 
    private void findPermutations(int[] nums,
                                  HashMap<Integer,Integer> originalOccurrences,
                                  HashMap<Integer,Integer> curOccurrences, 
                                  ArrayList<Integer> curList,
                                  List<List<Integer>> results) {
        
        if (curList.size() == nums.length) {
            results.add(new ArrayList<>(curList));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // 把重复扼杀在摇篮里
            if (i == 0 || nums[i] != nums[i - 1]) {
                int n = nums[i];
                // 不能超过这个数在nums里出现过的次数
                if (curOccurrences.getOrDefault(n,0) < originalOccurrences.get(n)) {
                    // add n
                    curList.add(n);
                    curOccurrences.put(n, curOccurrences.getOrDefault(n,0) + 1);
                    
                    // next step for constructing the permutations
                    findPermutations(nums, originalOccurrences, curOccurrences, curList, results);
                    
                    // remove n
                    curList.remove(curList.size() - 1);
                    curOccurrences.put(n, curOccurrences.get(n) - 1);
                }
            }
        }
    }
    
    
    
    
    
}

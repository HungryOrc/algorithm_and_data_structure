

/* Given a set of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Notice
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.

Example
Given candidate set [2,3,6,7] and target 7, a solution set is:
[7]
[2, 2, 3] */

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        
        Arrays.sort(candidates);
        
        dfs(candidates, 0, target, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void dfs(int[] candidates, int startIndex, int remainTarget,
        ArrayList<Integer> combination, List<List<Integer>> result) {
        if (remainTarget == 0) {
            result.add(new ArrayList<Integer>(combination)); // 注意！要new一个！
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            
            if (candidates[i] > remainTarget) {
                return;
            }
        
            combination.add(candidates[i]);
            // 重要！一个元素要无限次复用，
            // 下一次 recursion 的 start index 就必须与这一次的相同！不变！！！
            dfs(candidates, i, remainTarget - candidates[i], combination, result);
            combination.remove(combination.size() - 1);
        }
    }
}

// 数组里的元素可能有重复
// 在一个组合里，每个元素都可以出现任意次。所以每个元素在数组里出现多少次其实没有意义
// 在一个组合里，总的元素个数不限
// 在一个组合里，每个元素的出现顺序如果改变，还算是同一个组合

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
    
    // 方法1：数组不去重
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        
        Arrays.sort(candidates);
        
        dfs(candidates, 0, target, new ArrayList<Integer>(), result);
        return result;
    }
    private void dfs(int[] candidates,
                int startIndex,
                int curTarget,
                ArrayList<Integer> combination,
                List<List<Integer>> result) {
        
        if (curTarget == 0) {
            result.add(new ArrayList<Integer>(combination)); // 注意！要new一个！
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            
            if (candidates[i] > curTarget) {
                break;
            }
            
            // 精华所在！！！下面这个if语句是为了保证进入最终result的每个组合都是互不相同的！！！
            // 只有当数组里有重复元素时，才有这个if语句存在的必要！
            // 而这个if语句的存在，与数组里的每个元素能被使用一次还是无限次，无关！
            // 每个元素能被使用一次还是无限次，是靠下文的 startIndex 在下一轮dfs里
            // +1 还是 不+1 来区分实现的
            
            // 如果数组是 1, 2, 2, 4, 7, 9, 9, 9, 12
            //
            // 如果当前的 start index 在 1 那里，则在本次的 for 循环里，
            // 第一个2会入选，第二个2不会入选；第一个9会入选，第二三个9不会入选
            //
            // 如果当前的 start index 在 第一个2 那里，则在本次的 for 循环里，
            // 第一个2会入选，第二个2不会入选；第一个9会入选，第二三个9不会入选
            //
            // 如果当前的 start index 在第二个2 那里，则在本次的 for 循环里，
            // 第二个2会入选；第一个9会入选，第二三个9不会入选
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
        
            combination.add(candidates[i]);
            // 重要！一个元素要无限次复用，
            // 下一次 recursion 的 start index 就必须与这一次的相同！不变！！！
            dfs(candidates, i, curTarget - candidates[i], combination, result);
            combination.remove(combination.size() - 1);
        }
    }
    
    
    
    
    
    
}

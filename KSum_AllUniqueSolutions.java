/* 数组里没有重复数字。返回所有合格的k-number组合。

Given n unique integers, number k (1 <= k <= n) and target.
Find all possible k integers where their sum is target.

Example
Given [1,2,3,4], k = 2, target = 5. Return:
[
  [1,4],
  [2,3]
]                          */

// 方法：朴素的 DFS，没什么花活儿，一直DFS到底，不借助 4sum或者2sum
public class Solution {

    public ArrayList<ArrayList<Integer>> kSumII(int[] array, int k, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (array == null || array.length == 0 || k <= 0) {
            return result;
        }
        
        dfs(array, 0, new ArrayList<Integer>(), k, target, result);
        return result;
    }
    
    private void dfs(int[] array, int curIndex, List<Integer> curList, 
        int k, int remainTarget, ArrayList<ArrayList<Integer>> result) {
        
        // 注意胜利结束的条件 ！
        if (k == 0 && remainTarget == 0) {
            result.add(new ArrayList<Integer>(curList));
            return;
            
        // 特别注意失败结束的条件 ！！！
        } else if (k == 0 || remainTarget <= 0 || curIndex == array.length) {
            return;
        }
        
        // Case 1: 不加入 curIndex 处的数字
        dfs(array, curIndex + 1, curList, k, remainTarget, result);
        
        // Case 2: 加入 curIndex 处的数字
        curList.add(array[curIndex]);
        dfs(array, curIndex + 1, curList, k - 1, remainTarget - array[curIndex], result);
        curList.remove(curList.size() - 1);
    }
}

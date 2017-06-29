/* Given an array of n numbers, with no duplicate value, find all possible combinations of k elements in the array.
For example, if input array is {2, 1, 3, 4}, and k = 2, then the output should be:
{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.  */

public class Solution {
     
    /* DFS, 用 Recursive Searching Tree 来做
       核心思路：看下一个位置可以放谁
       
                       []
                    /   |   \
                [1]    [2]    [3]
             /   \     /
        [1,2] [1,3]  [2,3]
         /
    [1,2,3]
  
    时间复杂度：
     = O (答案的个数 * 得到每个答案所需的时间)
     = O (2^n * n)
     得到每个答案所需的时间是 O(n)，是因为可能要把n个数都add到一个答案里去 */
  
    public ArrayList<ArrayList<Integer>> subsetsOfSizeK(int[] nums) {
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        
        Arrays.sort(nums); // 在这一题里其实可有可无，只是美化结果
        dfs(nums, 0, new ArrayList<Integer>(), k, results);
        return results;
    }
    
    private void dfs(int[] nums, int startIndex, 
                     ArrayList<Integer> subset, int k, ArrayList<ArrayList<Integer>> results) {
                         
        if (subset.size() == k) { // 结束条件
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);          
            dfs(nums, i + 1, subset, results); // 这里是 i + 1 ！ 不是 curPos + 1 ！
            subset.remove(subset.size() - 1);
        }
    }
}

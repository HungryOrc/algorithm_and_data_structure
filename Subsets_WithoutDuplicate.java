/* Given a set of distinct integers, return all possible subsets.
Notice:
1. Elements in a subset must be in non-descending order.
2. The solution set must not contain duplicate subsets.

Example:
If S = [1,2,3], a solution is:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
] */

class Solution {
     
    /* 方法1：DFS, 用 Recursive Searching Tree 来做
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
  
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        
        Arrays.sort(nums); // 在这一题里其实可有可无，只是美化结果
        dfs(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }
    
    // 递归的三要素：定义、拆解、出口
    
    // 1. 递归的定义：这个递归到底做了什么事情？
    // 把所有以 subset 开头的集合，都放到 results 里去
    private void dfs(int[] nums, int startIndex, 
                     ArrayList<Integer> subset,
                     ArrayList<ArrayList<Integer>> results) {
                         
        // 2. 递归的拆解
        // 先要把 subset 本身扔到 results 里面去！
        results.add(new ArrayList<Integer>(subset));
        // 不可以直接add ！！  results.add(subset) 是不行的 ！！
        // 因为 subset 是一个 reference！后面所有对 subset 的操作，
        // 即所谓的“后效”，都会影响此处被加到 results 里的 subset！
        
        for (int i = startIndex; i < nums.length; i++) {
            
            subset.add(nums[i]);          
            // 注意！！！这里是 i+1 ！！不是 curPos+1 ！！
            dfs(nums, i + 1, subset, results);
            // 递归搜索的精华所在：在下一步操作之前，把之前的影响清空！！
            subset.remove(subset.size() - 1);
        }
        
        // 3. 递归的出口
        // 这道题里并没有特意的出口处理
    }
    
    
    // 方法2：也是DFS。着眼于 2^n 种答案这样的思维角度，
    // 对于n个元素中的每一个来说，它都有2种命运：被采纳，和不被采纳
    // 下面的题目是一个String里的chars组成各种subset，和上面题目中用numbers组成各种subsets是一样的道理
  
    public List<String> subSets(String set) {
      List<String> result = new ArrayList<>();
      if (set == null) {
        return result;
      }

      StringBuilder sb = new StringBuilder();
      getAllSubsets(set, 0, sb, result);
      return result;
    }

    private void getAllSubsets(String set, int curIndex, StringBuilder sb, List<String> result) {
      if (curIndex == set.length()) {
        result.add(sb.toString());
        return;
      }

      char curChar = set.charAt(curIndex);

      getAllSubsets(set, curIndex + 1, sb, result);

      sb.append(curChar);
      getAllSubsets(set, curIndex + 1, sb, result);
      sb.deleteCharAt(sb.length() - 1);
    }
  
}

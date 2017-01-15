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
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
     
    /* DFS, Recursive Searching Tree 方法
    
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
     得到每个答案所需的时间是 O(n)，是因为可能要把n个数都add到一个答案里去
    */
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
        // 不可以 results.add(subset) ！！
        // 因为 subset 是一个 reference！后面所有对 subset 的操作，
        // 即所谓的“后效”，都会影响此处被加到 results 里的 subset！
        
        for (int i = startIndex; i < nums.length; i++) {
            
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, results);
    
            // 递归搜索的精华所在：在下一步操作之前，把之前的影响清空！！
            subset.remove(subset.size() - 1);
            
            /*
            // 如果不想反复使用 subset，反复往它里面加减元素
            // 则可以用下述做法：每次复制一个新的 subset 出来，
            // 再加元素。这样就不用再减元素了。也不用反复使用一个set
            // 但这样做速度更慢，占用空间也更多
            ArrayList<Integer> addSet = new ArrayList<>(subset);
            addSet.add(nums[i]);
            dfs(nums, i + 1, addSet, results);
            */
        }
        
        // 3. 递归的出口
        // 这道题里并没有特意的出口处理
    }
    
    
    // ----------------------------------------------------------------------------------
  
    // 用排列来穷举的方法。不如上面的快
    class Solution {

        public ArrayList<ArrayList<Integer>> subsets(int[] nums) {

            Arrays.sort(nums);
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            pickSubsets(nums, 0, new ArrayList<Integer>(), result);

            return result;
        }
        private static void pickSubsets(int[] nums, int curPos,
            ArrayList<Integer> curList, ArrayList<ArrayList<Integer>> result)
        {
            if (curPos == nums.length) // 意味着上一个循环已经搞到了length-1
            {
                result.add(curList);
                return;
            }

            // situation 1: not adding the number at curPos to curList
            pickSubsets(nums, curPos+1,  curList, result);

            // situation 2: adding the number at curPos to curList
            ArrayList<Integer> addCurList = new ArrayList<>(curList);
            addCurList.add(nums[curPos]);
            pickSubsets(nums, curPos+1, addCurList, result);
        }
    }
  
}

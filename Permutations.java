/* Given a list of numbers, return all possible permutations.
Notice: You can assume that there is no duplicate numbers in the list.

Example:
For nums = [1,2,3], the permutations are:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
] */

// Ref: http://www.jiuzhang.com/solutions/permutations/

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
     
    // Recursion 方法
    public List<List<Integer>> permute(int[] nums) {
        
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // 注意！
        // nums 为空时，结果是空的 List of Lists
        // nums 不为空但长度为0时，结果是长度为 1 的List of Lists，其内容为一个空List
        if (nums == null) {
            return result;
        }
        else if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        findPermutations(nums, new ArrayList<Integer>(), result);
        return result;
    }
    private void findPermutations(int[] nums,
                                  ArrayList<Integer> curList,
                                  ArrayList<List<Integer>> result) {
                                      
        if (curList.size() == nums.length) {
            // 注意！！一定要复制！！
            // 因为 curList 在其他分支里还会被反复利用！！不是到这里就使命终结了！！
            result.add(new ArrayList<Integer>(curList));
            return;
        }
              
        // 整个算法的精华在这里！把每个数都轮流放进去！！！
        // 然后用ArrayList.contains 函数来判断是否有过这个数了！！！
        for (int n : nums) {
            // ArrayList 也有 contains 函数！不光是 HashSet 有！
            if (!curList.contains(n)) {
                curList.add(n);
                findPermutations(nums, curList, result);
                curList.remove(curList.size() - 1);
            }
        }                       
    }
}

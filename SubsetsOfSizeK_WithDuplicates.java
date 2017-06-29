// 更详细的解释，见 Subsets_With Duplicates

public class Solution {
    
    /* @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets. */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        
        if (nums == null || nums.length == 0)
            return results;
        
        Arrays.sort(nums); // nums里有重复，所以这个排序必不可少
        dfs(nums, 0, k, new ArrayList<Integer>(), results);
        return results;
    }
    
    private void dfs(int[] nums, int curPos,
                     ArrayList<Integer> subset, int k, ArrayList<ArrayList<Integer>> results) {
        
        if (subset.size() == k) { // 与无size k限制的题目相比，区别就只在这一句
            results.add(new ArrayList<Integer>(subset));
        }
        
        for (int i = curPos; i < nums.length; i++) {
            
            // 精华所在！！！很巧妙！！！
            if (i > curPos && nums[i] == nums[i - 1]) {
                continue;
            }
            
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, results); // 这里是 i+1 ！！不是 curPos+1 ！！
            subset.remove(subset.size() - 1);
        }
    }       
}

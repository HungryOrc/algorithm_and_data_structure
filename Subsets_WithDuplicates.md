/* Given a list of numbers that may has duplicate numbers, return all possible subsets
Notice:
1. Each element in a subset must be in non-descending order.
2. The ordering between two subsets is free.
3. The solution set must not contain duplicate subsets.

Example:
If S = [1,2,2], a solution is:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
] */

```
			       []
		      /      /    \      \
		 [1]       [2]   2:不采用  [3]
	       /   \      /   \
	   [1,2] [1,3]  [2,2] [2,3]
	  /    \          /
      [1,2,2] [1,2,3]   [2,2,3]
        / 
    [1,2,2,3]	
```

```java
class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        
        if (nums == null || nums.length == 0)
            return results;
        
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }
    private void dfs(int[] nums,
                     int curPos,
                     ArrayList<Integer> subset,
                     ArrayList<ArrayList<Integer>> results) {
                         
        results.add(new ArrayList<Integer>(subset));
        
        for (int i = curPos; i < nums.length; i++) {
            
            // 精华所在！！！很巧妙！！！
            if (i > curPos && nums[i] == nums[i - 1]) {
                continue;
            }
            
            subset.add(nums[i]);
	          // 注意！！！这里是 i+1 ！！不是 curPos+1 ！！
            dfs(nums, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
    }       
}
```

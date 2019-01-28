
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

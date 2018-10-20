  
  
    // 方法2：DFS Recursion
    // Ref: http://www.jiuzhang.com/solutions/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        boolean[] visited = new boolean[nums.length]; // 默认都是false
        dfs(nums, new ArrayList<Integer>(), visited, result);
        return result;
    }
  
    private void dfs(int[] nums, List<Integer> curList, 
                     boolean[] visited, List<List<Integer>> result) {
      
        if (curList.size() == nums.length) {
            // 一定要复制！
            // 因为 curList 在其他分支里还会被反复利用！不是到这里就使命终结了
            result.add(new ArrayList<Integer>(curList));
            return;
        }
              
        // 关键在此。每一次都是试图把整个数组里的每一个数都轮流放进去！
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == false) {
                curList.add(nums[i]);
                visited[i] = true;
              
                findPermutations(nums, curList, visited, result);
              
                curList.remove(curList.size() - 1); // 复原
                visited[i] = false; // 复原
            }
        }                       
    }
  
  

    public List<List<Integer>> permute(int[] nums) {
      
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        } else if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        int len = nums.length;
        // 把数组里的第一个数放进去，作为后面所有操作的基础
        ArrayList<Integer> firstList = new ArrayList<>();
        firstList.add(nums[0]);
        results.add(firstList);
        
        // 依次把第 2 个到第 n 个数放进去
        for (int i = 1; i < len; i++) {
            int curValue = nums[i];
            List<List<Integer>> tmpResults = new ArrayList<>();
            
            // for each list in the current temp result
            for (List<Integer> curList : results) {
                int curListLen = curList.size();
                
                // for each possible position that we can insert the current value into
                for (int j = 0; j <= curListLen; j++) {
                    List<Integer> tmpList = new ArrayList<>(curList); // 每一次都要new一个！
                    tmpList.add(j, curValue);
                    tmpResults.add(tmpList);
                }
            }
            results = tmpResults;
        }
      
        // 完毕。返回最后的总结果
        return results;
    }
    
    
    // 方法4：Non-Recursion 方法，九章版本
    // 
    public List<List<Integer>> permute(int[] nums) {
      
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums == null) {
            return permutations;
        } else if (nums.length == 0) {
            permutations.add(new ArrayList<Integer>());
            return permutations;
        }
        
        int n = nums.length;
        ArrayList<Integer> stack = new ArrayList<>();
        
        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            
            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }
            
            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }
            
            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums[stack.get(i)]);
            }
            permutations.add(permutation);
        }
        return permutations;
    } 
}

// 数组里的元素不重复
// 在一个组合里，每个元素都必须且只能出现一次
// 在一个组合里，总的元素个数等于数组里元素的个数
// 在一个组合里，每个元素的出现顺序如果改变，就视为新的组合

/* Given an Array of numbers, return all possible permutations.
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

class Solution {
     
    // 方法1：Recursion。DFS的套路
    // Ref: http://www.jiuzhang.com/solutions/permutations/
    public List<List<Integer>> permute(int[] nums) {
        
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        // nums 为空时，结果是空的 List of Lists
        // nums 不为空但长度为0时，结果是长度为 1 的List of Lists，其内容为一个空List
        if (nums == null) {
            return result;
        }
        else if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        boolean[] visited = new boolean[nums.length];
        findPermutations(nums, new ArrayList<Integer>(), visited, result);
        return result;
    }
  
    private void findPermutations(int[] nums, ArrayList<Integer> curList, ArrayList<List<Integer>> result) {
      
        if (curList.size() == nums.length) {
            // 注意！！一定要复制！！
            // 因为 curList 在其他分支里还会被反复利用！！不是到这里就使命终结了！！
            result.add(new ArrayList<Integer>(curList));
            return;
        }
              
        // 整个算法的精华在这里！每一次都是试图把整个数组里的每一个数都轮流放进去！！！
        // 只是一开始要用 ArrayList.contains 函数来判断是否有过这个数了！！！
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
  
  
    /* 方法2：Laioffer 的 swap DFS 方法
    
     Time: O(n * n!), n is the number of characters in the input String. Because we have n! kinds of permutations, 
     and to get each answer, we need O(n) time，
     每个答案所需的 n 的时间是消耗在：每个答案得到以后，要用n的时间来造new String来固化这个答案
     至于得到每个答案的时间，其实是O(1)，因为通过swap方法，不需要每个答案都耗费n的时间，具体解释如下：
     比如说要找1,2,3这三个数以功能组成多少个排列（答案是6种），那么swap方法的解题过程其实是：
     
         1         2         3
        / \       / \       / \
       2   3     1   3     1   2
       
      再往下还有一层，但前两层定了以后，第三层就已经定了。定前两层的时间消耗，可以看出，是 O(6)，与答案的个数是一个量级的 ！！！ 
      不过在数学上说，n*n! 和 n! 其实是一个量级的，所以这一题的答案也可写为 O(n!)
      
      Space: O(n). Because we need n call stacks, and each call stack requires constant space. */
  
    public class Solution {

      public List<String> permutations(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
          return result;
        }

        char[] cArray = set.toCharArray();
        dfsWithSwapping(cArray, 0, result);
        return result;
      }

      private void dfsWithSwapping(char[] cArray, int curIndex, List<String> result) {
        
        if (curIndex == cArray.length) {
          // 这一步，造新String，耗时是 O(n) ！！！
          // 分析时间复杂度时，别忘了这种看似不起眼，其实能量很大的地方 ！！！
          result.add(new String(cArray)); 
          return;
        }

        for (int i = curIndex; i < cArray.length; i++) {

          swap(cArray, curIndex, i);

          dfsWithSwapping(cArray, curIndex + 1, result);

          swap(cArray, curIndex, i); // 复原
        }
      }

      private void swap(char[] cArray, int i, int j) {
        char c = cArray[i];
        cArray[i] = cArray[j];
        cArray[j] = c;
      }
    } 
  
  
    // 方法3：Non-Recursion 方法
    // Ref: https://discuss.leetcode.com/topic/6377/my-ac-simple-iterative-java-python-solution
    /* To permute n numbers, we can add the n-th number into the resulting List<List<Integer>> composed by the 
     1st, 2nd, 3rd ... till the (n-1)th numbers, in every possible position.
     For example, if the input num[] is {1,2,3}. 
     (i) Add 1 into the initial List<List<Integer>> (let's call it "answer").
     (ii) 2 can be added in front or after 1. So we have to copy the List<Integer> in answer (it's just {1}), then
         add 2 in position 0 of {1}; then copy the original {1} again, and add 2 in position 1. 
         Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.
     (iii) Now we have to add 3. First copy {2,1} and {1,2}, add 3 in position 0; 
         then copy {2,1} and {1,2}, and add 3 into position 1; 
         then do the same thing for position 3.
     Finally we have 2*3=6 lists in answer, which is what we want. */
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
    // Ref: http://www.jiuzhang.com/solutions/permutations/
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

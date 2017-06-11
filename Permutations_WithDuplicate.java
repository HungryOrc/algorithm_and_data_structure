// 数组里的元素可能有重复
// 在一个组合里，每个元素（包括每一个重复的元素）都必须且只能出现一次
// 在一个组合里，总的元素个数等于数组里元素的个数
// 在一个组合里，每个元素的出现顺序如果改变，就视为新的组合

/* Given a list of numbers with duplicate number in it. Find all unique permutations.

Example:
For numbers [1,2,2] the unique permutations are:
[
  [1,2,2],
  [2,1,2],
  [2,2,1]
] 

Follow up:
Can you do it without recursion? That would be great!!
还没找到java的非recursion解法！！ */

public class Solution {
    
    /* 方法1：Laioffer 的 swap DFS 方法。最优先选用这个方法解题 ！！！ 且不用先sort ！
     具体的说明，参考我的Git总结的 没有重复的permutation那一题：
     https://github.com/HungryOrc/Algorithm/blob/master/Permutations_NoDuplicate.java    
     
     与数组里元素不重复的permutation的swap做法相比，解法上的区别在于：
     在每一轮新的 swap loop 里，设置一个 HashSet，记录在本轮swap loop里被swap过的各个元素，
     已经swap过的元素，就不再swap到同样的这个位置上。这是回头看很容易理解的。但一开始要想到就不容易    */
    public List<String> permutations(String set) {
      List<String> result = new ArrayList<>();
      // corner cases
      if (set == null) {
        return result;
      } else if (set.length() == 0) {
        result.add("");
        return result;
      }

      char[] cArray = set.toCharArray();
      findAllPermuWithSwap(cArray, 0, result);

      return result;
    }

    private void findAllPermuWithSwap(char[] cArray, int startIndex,
      List<String> result) {
      // base case
      if (startIndex == cArray.length) {
        result.add(new String(cArray));
        return;
      }

      // 特别注意 ！！！ 这个 HashSet 是定义在这里的 ！！！ 为每一个 swap loop 定义一个 Set ！！！
      HashSet<Character> swappedChars = new HashSet<>();
      for (int i = startIndex; i < cArray.length; i++) {
        if (!swappedChars.contains(cArray[i])) {
          swap(cArray, startIndex, i);
          findAllPermuWithSwap(cArray, startIndex + 1, result);
          swap(cArray, startIndex, i);

          swappedChars.add(cArray[i]); // 此时再把这个char加到Set里去 ！！
        }
      }
    }

    private void swap(char[] cArray, int i, int j) {
      char tmp = cArray[i];
      cArray[i] = cArray[j];
      cArray[j] = tmp;
    }


    // 方法2：九章式 DFS Recursion
    // 与数组里元素不重复的permutation相比，解法上多了两点：
    // 1. 记录每个元素的使用情况的数组 visited
    // 2. 在dfs的for循环里，要加上 i > 0 && nums[i] == nums[i - 1] && visited[i-1] == 0 
    //    这个判断语句，来避免重复的元素生成出重复的排列
    // Ref: http://www.jiuzhang.com/solutions/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();    
        if (nums == null) {
            return results;
        } else if(nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

        Arrays.sort(nums); // 别忘了！这种解法要求数组必须是排好序的 ！！

        int[] visited = new int[nums.length]; 
     
        dfs(results, new ArrayList<Integer>(), visited, nums);    
        return results;
    }
  
    private void dfs(ArrayList<List<Integer>> results, ArrayList<Integer> list, int[] visited, int[] nums) {
        
        if(list.size() == nums.length) {
            results.add(new ArrayList<Integer>(list)); // 别忘了new一个！
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            
            /* 这个if语句是为了去除重复元素
             比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
             我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
             当中也应该排在前面，这样就保证了唯一性。当前面的2还没有被使用的时候，就不应该让后面的2被使用 */
            if (visited[i] == 1 || // 如果本数已经被使用过，那么也跳过不管
                (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)) {
                continue;
            }
           
            // 下面开始正式干活了 ！
            list.add(nums[i]);
            visited[i] = 1;
            
            dfs(results, list, visited, nums);
            
            list.remove(list.size() - 1); // 复原
            visited[i] = 0; // 复原
        }
    }
  
}

/* Determine if there exists three elements in a given array that sum to the given target number. 
Return all the triple of values that sums to target.
Assumptions:
The given array is not null and has length of at least 3
No duplicate triples should be returned, order of the values in the tuple does not matter

Examples:
A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]   */


/* 思路：在 2sum的外面包一层，就成了3sum。下面的代码的2sum是用了hashset的方法来做的。

这一个3sum要求的是所有不重复的三元组，要精确到每一个三元组里的每一个元素的值。但是原数组里可能有重复的数。这样就必须去重了！！！
这个3sum问题的去重，与2sum的不同之处在于，它分为两步：
第一，要把原数组排序
第二，每次挑选三元组里的第一个数的时候，要看它和它之前的数是否相等，如果相等，就什么也不做，直接跳到下一个数去 ！！！   */

public class Solution {
  
  public List<List<Integer>> allTriples(int[] array, int target) {
    List<List<Integer>> result = new ArrayList<>();
    
    Arrays.sort(array); // 去重二部曲之一 ！！！
    
    for (int i = 0; i < array.length - 2; i++) {
      
      // 去重二部曲之二 ！！！
      if (i > 0 && array[i] == array[i - 1]) {
        continue;
      }
      
      int curTarget = target - array[i];
      
      HashSet<Integer> records = new HashSet<>();
      boolean foundHalfTarget = false;
      
      // 从 i 之后的第一个数开始
      for (int j = i + 1; j < array.length; j++) {
        
        if (!records.contains(array[j])) {
          if (records.contains(curTarget - array[j])) {
            List<Integer> triplet = new ArrayList<>();
            triplet.add(curTarget - array[j]);
            triplet.add(array[j]);
            triplet.add(array[i]);
            result.add(triplet);
          }
          
          records.add(array[j]);
        }
        else {
          // 注意！这里不要用 num == target / 2 ！ 否则会出现 7 / 2 = 3 这样的错误情况
          if (array[j] * 2 == curTarget && foundHalfTarget == false) {
            List<Integer> triplet = new ArrayList<>();
            triplet.add(array[j]);
            triplet.add(array[j]);
            triplet.add(array[i]);
            result.add(triplet);
            
            foundHalfTarget = true;
          }
        }
      }
    }
    
    return result;
  }
}

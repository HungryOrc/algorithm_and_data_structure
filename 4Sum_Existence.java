/* 条件：
给的元素可能有重复。可以是正负整数或小数。
每个元素最多用一次。

Determine if there exists a set of four elements in a given array that sum to the given target number.
Assumptions: The given array is not null and has length of at least 4

Examples:
A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)
A = {1, 2, 2, 3, 4}, target = 12, return false  */


/* 思路：很巧妙！用HashMap 以及2层for-loop，时间 O(n^2) 就搞定了！

把想象中的4个数分为2个pair，分别为 p1L, p1R, p2L, p2R. 它们的和需要等于特定的target。然而还有一个重要的要求，就是
p2L 的index 必须 > p1R 的index ！！！   */

// helper class
class Pair {
  int leftIndex, rightIndex;
  
  public Pair(int leftIndex, int rightIndex) {
    this.leftIndex = leftIndex;
    this.rightIndex = rightIndex;
  }
  
}

public class Solution {
  
  public boolean fourSum(int[] array, int target) {
    
    // <sum of pair, entity of pair>
    HashMap<Integer, Pair> pairs = new HashMap<>();
    
    // 精华1！！！ 
    // 必须先设定当前pair（即2个pair中靠右边的一个pair）的right index，再loop 当前pair的 left index！！！
    // 如果反其道而行之，则会很被动！
    for (int r = 1; r < array.length; r++) { // right index of right pair
      for (int l = 0; l < r; l++) { // left index of right pair
        int curSum = array[l] + array[r];
        
        if (pairs.containsKey(target - curSum) &&
          // right index of previous pair (namely left pair) must < left index of current pair (namely right pair)
          pairs.get(target - curSum).rightIndex < l) { 
          return true;
        }
        
        // 精华2！！！
        // 这样的处理，使得我们在 hashmap 里，sum相同的所有pairs，我们将会只存right index最小的那个pair ！！！
        // 这样才能保证我们一定不会漏掉可能的答案
        // 这里也是为什么我们要先loop right index，再loop left index 的原因之一！
        if (!pairs.containsKey(curSum)) {
          pairs.put(curSum, new Pair(l, r));
        }
      }
    }
    return false;
  }
}

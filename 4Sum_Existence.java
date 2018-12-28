
看lintcode或者laicode上有没有这题

然后在leetcode和lintcode上搜索 4 sum，four sum


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

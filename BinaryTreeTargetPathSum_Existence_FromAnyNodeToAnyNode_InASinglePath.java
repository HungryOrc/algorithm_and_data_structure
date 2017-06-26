/* 这一题的详细思路，参考我总结的另一题：
Binary Tree Target Path Sum_All Paths_From Any Node To Any Node_In A Single Path

Given a binary tree in which each node contains an integer number. 
Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), 
the sum of the numbers on the path is the given target number.

Examples
    5
  /    \
2      11
     /    \
    6     14
  /
 3
 
If target = 17, There exists a path 11 + 6, the sum of the path is target.
If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
If target = 10, There does not exist any paths sum of which is target.
If target = 11, There exists a path only containing the node 11.  */


/* 思路：Prefix Sum + HashSet
时间: O(n)
空间: O(n)

HashSet储存在本条单路径（从root到某个leaf的一条不上下折返的路径）上，以前出现过的所有prefix sum。
这个HashSet必须首先存一个0，因为没有任何node进来的时候，即在整颗树的root进来之前，就应该放一个0，这样就能处理从root开始的prefix Sum。

然后另外一个重点，在进入下一层的dfs之前，要把含有当前node的value的prefix sum放到hash set里面去，
在下一层的dfs完成后，要把这个prefix sum再从hash set里拿掉，
但有可能在本node之前，同样的prefix sum就已经出现过在这个hash set里了，这样的话，
往set里放2个重复的数并没有意义，而最后要往外拿掉此数的时候，就不能拿掉了，因为它原本之前就在里面的，不是因为本node才在里面的，不应该被拿掉。
比如到某node为止的prefix sum是4，而此node的爷爷节点处的prefix sum也是4，那么这个4，在处理了当前node的children的dfs以后，就不应该
从set里被拿掉，因为它是之前更上层的node的prefix sum，理应继续传递到当前node的同层兄弟nodes那里去   */

public class Solution {

  public boolean exist(TreeNode root, int target) {
    HashSet<Integer> prevPrefixSums = new HashSet<>();
    prevPrefixSums.add(0);
    return lookFor(root, 0, prevPrefixSums, target);
  }
  
  private boolean lookFor(TreeNode node, int prevPrefixSum, 
    HashSet<Integer> prevPrefixSums, int target) {
  
    if (node == null) {
      return false;
    }
      
    int curPrefixSum = prevPrefixSum + node.key;
    if (prevPrefixSums.contains(curPrefixSum - target)) {
      return true;
    }
    
    boolean existedBefore = false;
    if (prevPrefixSums.contains(curPrefixSum)) {
      existedBefore = true;
    } else {
      prevPrefixSums.add(curPrefixSum);
    }
    
    boolean result = (
      lookFor(node.left, curPrefixSum, prevPrefixSums, target) || 
      lookFor(node.right, curPrefixSum, prevPrefixSums, target));
      
    if (existedBefore == false) {
      prevPrefixSums.remove(curPrefixSum);
    }
    
    return result;
  }
}

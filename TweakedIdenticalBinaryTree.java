/* Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. 
A tweak is defined as a swap of the children of one node in the tree.

Examples:
        5
      /    \
    3        8
  /   \
1      4
and:
        5
      /    \
    8        3
           /   \
          1     4
these 2 binary trees are tweaked identical. */

/* public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * } */
 
/* laioffer的方法

时间：O(n^2)，因为：
Recursion Tree 一共有 log2(n) 层，每层是 4 个 分 支 ！！！ 所以第 m 层是 4^m 个nodes。
然后 Recursion Tree 的最后一层的总nodes的量级，等于整个 Recursion Tree 的总nodes的量级。
所以整个 Recursion Tree 一共的nodes数等于 第log2(n)层 的nodes数，即：
4^(log2(n)) = 2^(2 * log2(n)) = 2^(log2(n^2)) = n^2
因为这个算法时间上要遍历每一个node，所以他的时间复杂度是 O(n^2)

空间：O(height of tree)，因为一共有 height of tree 层 call stack */

public class Solution {
  
  public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
    if (one == null && two == null) {
      return true;
    }
    else if (one == null || two == null) {
      return false;
    }
    else if (one.key != two.key) {
      return false;
    }
    
    return ((isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)) ||
            (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)));
  } 
}

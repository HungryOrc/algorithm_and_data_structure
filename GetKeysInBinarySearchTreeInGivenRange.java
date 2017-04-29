/* Get the list of keys in a given binary search tree in a given range[k1, k2] in ascending order, both min and max are inclusive.
Corner Cases: What if there are no keys in the given range? Return an empty list in this case.

Examples:
        5
      /    \
    3        8
  /   \        \
 1     4        11
get the keys in [2, 5] in ascending order, result is  [3, 4, 5]

* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* } */

/* Laioffer 的方法。对于BST，我们知道通过 in-order traversal 可以获得所有节点从小到大的排布。那我们就从这里入手，
做一个 “每一步都经过判断的，局部的 in-order traversal”

时间：O(logn + |k2 - k1|)
解释：这里的处理逻辑，其实可以认为是由2部分构成的：
(1) 在 BST 里查找 k1 和 k2
(2) 遍历 k2 - k1 的部分
而前面部分的开销和后面部分的开销，在不同情况下，不一定谁dominate。
比如刚好 k2 - k1 的部分没有任何一个node，这个时候前面的logn会dominate。如果刚好k2 - k1的部分包含了所有的node，那么这部分会dominate。
所以正确的复杂度是 logn + |k2 - k1|。
        
空间：O(logn)，因为一共有这么多层的 call stack */

public class Solution {
  
  public List<Integer> getRange(TreeNode root, int min, int max) {
    List<Integer> result = new ArrayList<Integer>();
    
    getRange(root, min, max, result);
    return result;
  }
  
  // overload
  private void getRange(TreeNode node, int min, int max, List<Integer> result) {
    if (node == null) {
      return;
    }
    
    // step 1: left subtree
    if (node.key > min) {
      getRange(node.left, min, max, result);
    }
    // step 2: current root, 当前root也要经受判断！不能直接用！
    if (node.key >= min && node.key <= max) {
      result.add(node.key);
    }
    // step 3: right subtree
    if (node.key < max) {
      getRange(node.right, min, max, result);
    }
  }
  
}

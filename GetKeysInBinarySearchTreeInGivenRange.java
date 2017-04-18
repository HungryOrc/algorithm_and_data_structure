/* Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.
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

// 对于BST，我们知道通过 in-order traversal 可以获得所有节点从小到大的排布。那我们就从这里入手，
// 做一个 “每一步都经过判断的，局部的 in-order traversal”
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

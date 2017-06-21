/* Insert a key in a binary search tree if the binary search tree does not already contain the key. 
Return the ROOT of the binary search tree! Not the node that has been inserted!

Assumptions:
There are no duplicate keys in the binary search tree
If the key is already existed in the binary search tree, you do NOTHING

Examples:

        5
      /    \
    3        8
  /   \
 1     4
 
insert 11, the tree becomes
        5
      /    \
    3        8
  /   \        \
 1     4       11
and return 5

insert 6, the tree becomes
        5
      /    \
    3        8
  /   \     /  \
 1     4   6    11
 and return 5                     */
 
 /* public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * } */
 
public class Solution {
  
  public TreeNode insert(TreeNode root, int key) {
    if (root == null) {
      return new TreeNode(key);
    }
    
    if (root.key == key) {
      return root;
    } 
    else if (root.key > key) {
      if (root.left == null) {
        root.left = new TreeNode(key);
      } else {
        insert(root.left, key); // 这里并不return anything ！！
      }
    } 
    else { // root.key < key
      if (root.right == null) {
        root.right = new TreeNode(key);
      } else {
        insert(root.right, key); // 这里并不return anything ！！
      }
    }
    
    return root; // 最终还是只要return the initial root ！！
  }
}

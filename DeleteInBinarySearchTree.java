/* Delete the target key K in the given binary search tree if the binary search tree contains K. 
Return the root of the binary search tree.
Find your own way to delete the node from the binary search tree, 
after deletion the binary search tree's property should be maintained.
Assumptions: There are no duplicate keys in the binary search tree       */

/* public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * } */

public class Solution {

  public TreeNode delete(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    
    if (root.key == key) {
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      } else if (root.right.left == null) {
        root.right.left = root.left;
        return root.right;
      } else {
        TreeNode smallest = findAndDeleteTheSmallest(root.right);
        smallest.left = root.left;
        smallest.right = root.right;
        return smallest;
      }
    }
    else if (root.key > key) {
      root.left = delete(root.left, key);
    }
    else { // root.key < key
      root.right = delete(root.right, key);
    }
    
    return root;
  }
  
  private TreeNode findAndDeleteTheSmallest(TreeNode root) {
    while (root.left.left != null) {
      root = root.left;
    }
    TreeNode smallest = root.left;
    
    root.left = smallest.right;
    smallest.right = null; // 这一步可以没有。不过有了更明晰
    
    return smallest;
  }
}

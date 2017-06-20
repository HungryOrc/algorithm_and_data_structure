/* Given K nodes in a binary tree, find their lowest common ancestor.
Assumptions: K >= 2

There is no parent pointer for the nodes in the binary tree
The given K nodes are guaranteed to be in the binary tree

Examples:
        5
      /   \
     9     12
   /  \      \
  2    3      14
The lowest common ancestor of 2, 3, 14 is 5
The lowest common ancestor of 2, 3, 9 is 9    */


// 因为这k个node都一定存在，所以我们可以用下面的方法：在从下到上的反馈过程中，最晚看到谁，就反馈谁 ！！！

public class Solution {
  
  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
    if (root == null) {
      return null;
    }
    if (nodes.contains(root)) {
      return root;
    }
    
    TreeNode outcomeFromLeftSubtree = lowestCommonAncestor(root.left, nodes);
    TreeNode outcomeFromRightSubtree = lowestCommonAncestor(root.right, nodes);
    
    if (outcomeFromLeftSubtree != null && outcomeFromRightSubtree != null) {
      return root;
    }
    if (outcomeFromLeftSubtree != null) { // && outcomeFromRightSubtree == null
      return outcomeFromLeftSubtree;
    } else { // outcomeFromLeftSubtree == null) && outcomeFromRightSubtree != null
      return outcomeFromRightSubtree;
    }
  }
}

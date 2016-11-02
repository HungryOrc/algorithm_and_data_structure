/* Invert a binary tree:
     4
   /   \
  2     7
 / \   / \
1   3 6   9
to:
     4
   /   \
  7     2
 / \   / \
9   6 3   1

Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}   */

// Reference: https://discuss.leetcode.com/topic/16039/straightforward-dfs-recursive-iterative-bfs-solutions/2

public class Solution {
     
     // Recursive DFS
     /* Time: O(n), Space: O(n)。理论上Recursive的方法应该较慢，但实测这个并不比后面的Iterative的方法慢
     This solution is bound to the application stack, which means it's no so much scalable, 
     you can find the problem size that will OVERFLOW the STACK and crash your application. 
     So more robust solution would be to use Stack data structure, as we show later.
     */
     public TreeNode invertTree_RecursiveDFS (TreeNode root) 
     {
          if (root == null)
               return null;
          TreeNode formerLeft = root.left;
          TreeNode formerRight = root.right;
          root.left = invertTree(formerRight);
          root.right = invertTree(formerLeft);
          return root;
     }
     
     
     

}




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
And return the reference of the root node

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
     /* Time: O(n), Space: O(n)。理论上Recursive的方法应该较慢，但实测这个其实比后面的2种Iterative的方法更快
     This solution is bound to the application stack, which means it's no so much scalable, 
     you can find the problem size that will OVERFLOW the STACK and crash your application. 
     So more robust solution would be to use Stack data structure, as we show later.
     */
     public TreeNode invertTree_RecursiveDFS (TreeNode root)  {
          if (root == null)
              return null;
          
          TreeNode formerLeft = root.left;
          TreeNode formerRight = root.right;
          root.left = invertTree_RecursiveDFS(formerRight);
          root.right = invertTree_RecursiveDFS(formerLeft);
          
          return root;
     }
     
     
     // Iterative DFS
     public TreeNode invertTree_IterativeDFS (TreeNode root) {
          if (root == null)
              return null;
          
          Stack<TreeNode> nodeStack = new Stack<TreeNode>();
          nodeStack.push(root);
          
          while (!nodeStack.isEmpty()) {
               TreeNode curNode = nodeStack.pop();
               // 接下来的三步就是swap
               TreeNode formerLeft = curNode.left;
               curNode.left = curNode.right;
               curNode.right = formerLeft;
               
               if (curNode.left != null)
                   nodeStack.push(curNode.left);
               if (curNode.right != null)
                   nodeStack.push(curNode.right);
          }
          return root;
     }

     
     // Iterative BFS
     public TreeNode invertTree_IterativeBFS (TreeNode root) {
          if (root == null)
              return null;
          
          Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
          nodeQueue.offer(root);
          
          while (!nodeQueue.isEmpty()) {
               TreeNode curNode = nodeQueue.poll();
               // 接下来的三步就是swap
               TreeNode formerLeft = curNode.left;
               curNode.left = curNode.right;
               curNode.right = formerLeft;
               
               if (curNode.left != null)
                   nodeQueue.offer(curNode.left);
               if (curNode.right != null)
                   nodeQueue.offer(curNode.right);
          }
          return root;
     }

}

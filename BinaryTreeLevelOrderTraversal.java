/* Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    
    // Iteration，用 Queue，BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty())
        {
            int numOfNodesInCurLevel = nodeQueue.size();
            ArrayList<Integer> valuesInCurLevel = new ArrayList<>();
            while (numOfNodesInCurLevel > 0)
            {
                TreeNode curNode = nodeQueue.poll();
                valuesInCurLevel.add(curNode.val);
                
                if (curNode.left != null)
                    nodeQueue.offer(curNode.left);
                if (curNode.right != null)
                    nodeQueue.offer(curNode.right);
                numOfNodesInCurLevel --; // 别忘了这个！！减减！
            }
            result.add(valuesInCurLevel);
        }
        return result;
    }
}

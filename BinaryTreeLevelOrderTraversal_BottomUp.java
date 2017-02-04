/* Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
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
     
    // 用 Queue（LinkedList）进行 BFS 操作，实现从左到右，从上到下
    // 然后把结果（存在 ArrayList 里的）垂直翻转一次，实现从下到上，从左到右
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
   
        ArrayList<List<Integer>> bottomToTop = new ArrayList<List<Integer>>();
        ArrayList<List<Integer>> topToBottom = new ArrayList<List<Integer>>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        
        if (root == null)
            return topToBottom;
        
        while (!nodeQueue.isEmpty()) {
            int numOfRemainingNodesInThisLevel = nodeQueue.size();
            ArrayList<Integer> nodeValuesInCurLevel = new ArrayList<>();
            
            while (numOfRemainingNodesInThisLevel > 0) {
                TreeNode curNode = nodeQueue.poll();
                nodeValuesInCurLevel.add(curNode.val);
                
                if (curNode.left != null)
                    nodeQueue.offer(curNode.left);
                if (curNode.right != null)
                    nodeQueue.offer(curNode.right);
                
                numOfRemainingNodesInThisLevel --;
            }
            topToBottom.add(nodeValuesInCurLevel);
        }
        
        for (int i = topToBottom.size()-1; i >= 0; i--)
            bottomToTop.add(topToBottom.get(i));
        return bottomToTop;
        
        /* 更销魂的做法：
        Collections.reverse(result); // 记住这个命令！直接反转自身！
        return result; */
    }
}

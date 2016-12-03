/* Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node. */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

public class Solution 
{
    // Iteration. BFS by Queue.
    // DFS by Stack就不写了，应该比BFS慢，因为必须DFS所有的paths才能得到最浅的结论
    public int minDepth(TreeNode root)
    {
        if (root == null)
            return 0;
        
        int curDepth = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        
        while (!nodeQueue.isEmpty())
        {
            int numOfNodesInCurLevel = nodeQueue.size();
            curDepth ++;
            
            while (numOfNodesInCurLevel > 0)
            {
                TreeNode curNode = nodeQueue.poll();
                
                if (curNode.left == null && curNode.right == null)
                    return curDepth;
                
                if (curNode.left != null)
                    nodeQueue.offer(curNode.left);
                if (curNode.right != null)
                    nodeQueue.offer(curNode.right);
                
                numOfNodesInCurLevel --;
            }
        }
        return -1; // actually we will never reach here
    }
    
}

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
    // 方法1: Recursion - Divide and Conquer 分治法
    public int minDepth(TreeNode root) 
    {
        if (root == null)
            return 0;

        // 特别注意！cur node的一个child为null时，不能说min depth到cur node就结束了
        // 如果cur node的另一个child不为null，则本path沿着另一个child继续下去了
        // 所以求 max depth of binary tree 的那种做法在求 min depth 的时候是不行的
        if (root.left == null)
            return minDepth(root.right) + 1;
        else if (root.right == null)
            return minDepth(root.left) + 1;
        else // 左右child都不是null
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    
    
    // 方法2: Recursion - Traversal
    
    
    
    
    // 方法3: Iteration. BFS by Queue.
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
            // 当前level内的node的个数
            int numOfNodesInCurLevel = nodeQueue.size();
            curDepth ++;
            
            while (numOfNodesInCurLevel > 0)
            {
                TreeNode curNode = nodeQueue.poll();
                
                // 找到一个leaf，那么此时找 min depth 的工作就可以结束了
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

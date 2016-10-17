/* Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node. */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 
public class MaxDepthOfBinaryTree {

    // 方法：Recursion。思路巧妙，代码简洁，但速度不快
    //
    public int maxDepth_ByRecursion(TreeNode root)
    {
        if(root == null)
            return 0;
        
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
    
    // 方法：Depth First Search (DFS)。使用了2个Stack，一个存Node，一个存正对应于当前Node的Depth
    // Node的Stack：每次先pop出父Node，再push进它的子Node(s)。
    // 后存入的Node先取出，取出以后就再也不存入了，如此实现了被处理过的Node不再被重复处理
    // 这样Node Stack里存的就是当前处理到的各个战线的最前锋的位置，他们的逐级父辈全都被扔掉了
    // 速度：很快
    // Reference: https://discuss.leetcode.com/topic/33826/two-java-iterative-solution-dfs-and-bfs/2
    //
    public int maxDepth(TreeNode root)
    {
        if(root == null)
            return 0;
        
        // 注意 class Stack 的初始化方式！
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        
        int maxDepth = 1;
        nodeStack.push(root);
        depthStack.push(1);
        
        while (!nodeStack.isEmpty())
        {
            TreeNode curNode = nodeStack.pop();
            int curDepth = depthStack.pop();
            
            maxDepth = Math.max(maxDepth, curDepth);
        
            if (curNode.left != null)
            {
                nodeStack.push(curNode.left);
                depthStack.push(curDepth + 1);
            }
            if (curNode.right != null)
            {
                nodeStack.push(curNode.right);
                depthStack.push(curDepth + 1);
            }
        }
        
        return maxDepth;
    }
    
    
}

/* Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node. */

/* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */
 
public class MaxDepthOfBinaryTree {

    // 方法1：Recursion - Divide and Conquer 分治法
    // 速度：按理说Recursion类方法的速度会偏慢，但实测相当快，leetcode的测试用例是1ms跑完
    public int maxDepth_ByRecursion(TreeNode root)
    {
        if(root == null)
            return 0;
        
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
    
    // 方法2: Recursion - Traversal
    public int depth;
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        findDepth(root, 1);
        return depth;
    }
    private void findDepth(TreeNode curNode, int curDepth) {
        if (curNode == null) {
            return;
        }
        if (curDepth > depth) {
            depth = curDepth;
        }
        findDepth(curNode.left, curDepth + 1);
        findDepth(curNode.right, curDepth + 1);
    }
 
 
    // 方法3：Depth First Search (DFS)。使用了2个Stack，一个存Node，一个存正对应于当前Node的Depth
    // Node的Stack：每次先pop出父Node，再push进它的子Node(s)。
    // 后存入的Node先取出，取出以后就再也不存入了，如此实现了被处理过的Node不再被重复处理
    // 这样Node Stack里存的就是当前处理到的各个战线的最前锋的位置，他们的逐级父辈全都被扔掉了
    // 速度：很快
    // Reference: https://discuss.leetcode.com/topic/33826/two-java-iterative-solution-dfs-and-bfs/2
    public int maxDepth_ByDFS(TreeNode root)
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
    
 
    // 方法4：Breadth First Search (BFS)。使用了1个Queue，放"当前Depth"层所含有的所有Nodes
    // 每次取出一个第m层的Node，就把它的子Node(s)(在第m+1层)放到Queue尾去。注意Queue是后进后出
    // 取出的Node就再也不存入了。处理完一层即一个Depth再处理下一层
    // 速度：很快
    // Reference: https://discuss.leetcode.com/topic/33826/two-java-iterative-solution-dfs-and-bfs/2
    public int maxDepth_ByBFS(TreeNode root)
    {
        if(root == null)
            return 0;
        
        // 注意 Java 里的 Queue 只是一个 Interface，不能被直接实例化！
        // 只能借助于一些implements Queue 的数据类型来进行实例化，包括 LinkedList：
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        
        int curDepth = 0;
        
        while (!nodeQueue.isEmpty())
        {
            int numOfRemainingNodesInCurDepth = nodeQueue.size();
            
            while (numOfRemainingNodesInCurDepth > 0)
            {
                TreeNode curNode = nodeQueue.poll();
                
                if (curNode.left != null)
                    nodeQueue.offer(curNode.left);
                if (curNode.right != null)
                    nodeQueue.offer(curNode.right);
                    
                numOfRemainingNodesInCurDepth --;
            }
            
            curDepth ++;
        }
        
        return curDepth;
    }

}

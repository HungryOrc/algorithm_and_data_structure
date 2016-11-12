/* You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, 
but it must go downwards (traveling only from parent nodes to child nodes).
The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
Return 3. The paths that sum to 8 are:
(1) 5 -> 3
(2) 5 -> 2 -> 1
(3) 3 -> 11

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    // 自然思路：取一个node A 作为起始点，用Stack进行DFS，找它下面的所有支路。
    // 注意！找到吻合sum时，计数加一，但还要继续往下找 ！！允许吻合之后继续偏离然后再次吻合 ！！这算两条吻合支路 ！！
    // 然后，再对node A 的 leftChild 和 rightChild 分别作如上的操作
    /* Time Complexity:
    Time Complexity should be O(N^2) for the worst case and O(NlogN) for balanced binary Tree.
    If the tree is balanced, then each node is reached from its ancestors (+ itself) only, which are up to log n. 
    Thus, the time complexity for a balanced tree is O (n * log n).
    However, in the worst-case scenario where the binary tree has the same structure as a linked list, 
    the time complexity is indeed O (n ^ 2). */
    public int pathSum(TreeNode root, int sum) {
        
        if (root == null)
            return 0;
        
        int numOfPaths = 0;
        
        // 这里以下用 Queue 做 BFS 也是一样的，用 Stack 还是用 Queue 是处理nodes的顺序的问题
        // 后面的 countPaths 函数才是如何处理每个node的问题
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        
        while (!nodeStack.isEmpty())
        {
            TreeNode curNode = nodeStack.pop();
            numOfPaths += countPaths(curNode, sum);
            
            if (curNode.left != null)
                nodeStack.push(curNode.left);
            if (curNode.right != null)
                nodeStack.push(curNode.right);
        }
        return numOfPaths;
    }
    
    private int countPaths(TreeNode curNode, int sum)
    {
        if (curNode == null)
            return 0;
        else if (curNode.val == sum) // 注意！这里就是上文提到的，吻合，计数加一，然后还得继续往下探！！
            return 1 + 
                countPaths(curNode.left, 0) + 
                countPaths(curNode.right, 0);
        else
            return countPaths(curNode.left, sum-curNode.val) + 
                   countPaths(curNode.right, sum-curNode.val);
    }


    // 非常巧妙的方法：Prefix Sum。比较难于理解
    // Ref: https://discuss.leetcode.com/topic/64388/simple-ac-java-solution-dfs/2
    // 从整个树的root开始，沿着每条path上的逐个node，一个一个加下去，加成prefixSum（注意从root到每个node的path是唯一的）
    // 比如一个path从root开始是 1(root),2,-1,-1,2，那么逐个的prefixSum依次是：1, 3, 2, 1, 3。把这些数都记录到 HashMap 里去
    // 如果加到某个node时，含有它在内的prefixSum减去targetSum的差，在之前的HashMap里出现过，
    // 即当前的prefixSum比targetSum多出来的部分，可以表示为本path上之前的某个node一直往上连加到root的和
    // 这意味着如果从那个node之后加到当前node，其和就正好是targetSum
    // 所以HashMap里有几个 prefixSum - targetSum，就往result里加几，即有几种加和方法可以等于targetSum
    // Time: O(n), it's a One-Pass method !!! 
    public int pathSum(TreeNode root, int sum) {
          
        Map<Integer, Integer> existingPrefixSums = new HashMap<>();
        // prefixSum为0的path必然至少有一个，即root node之前的prefixSum
        // 虽然看起来不应该算，但是到后面，出现了正好加和为targetSum的支路的时候，即 curPrefixSum == targetSum 的时候，
        // 下面的 backtrack 函数里就得处理到：
        // result = existingPrefixSums.getPrDefault(curPrefixSum - targetSum, targetSum) = existingPrefixSums.getPrDefault(0, targetSum)
        // 那么如果没有下面这句put(0, 1)，HashMap里那就取不到1，只能取到0，但其实是有了一个的，取0就错了
        existingPrefixSums.put(0, 1);
          
        return backtrack(root, 0, sum, map); 
    }
      
    // BackTrack - One Pass
    public int backtrack(TreeNode curNode, int curPrefixSum, int targetSum, Map<Integer, Integer> existingPrefixSums) {
        if(curNode == null)
            return 0;
          
        curPrefixSum += root.val;
        int result = existingPrefixSums.getOrDefault(curPrefixSum - targetSum, 0);
        existingPrefixSums.put(curPrefixSum, existingPrefixSums.getOrDefault(curPrefixSum, 0)+1);
        
        res += 
              backtrack(root.left, curPrefixSum, targetSum, existingPrefixSums) + 
              backtrack(root.right, curPrefixSum, targetSum, existingPrefixSums);
          
        // 这个语句是另外一个特别重要的点！
        // 因为每个path是不一样的，记录一个path上的逐个prefixSum的HashMap不应该和另一个path的HashMap有互相污染
        // 但是只要两个path之间有重叠部分，他们的HashMap就有相应重叠的部分，至少所有path都会经过root node
        // 那么如何消除不重叠的部分？比如走了右子path的子孙nodes，如何消除左子node的val？
        // 就靠下面这句了。在完成了左右分叉的计算以后，把当前node带来的prefixSum从HashMap里去掉，即消除了当前node曾经存在过的证据
        existingPrefixSums.put(curPrefixSum, existingPrefixSums.get(curPrefixSum)-1);
          
        return res;
    }

    
}

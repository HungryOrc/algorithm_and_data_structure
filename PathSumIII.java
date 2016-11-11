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


    // Prefix Sum, with HashMap
    // Ref: https://discuss.leetcode.com/topic/64388/simple-ac-java-solution-dfs/2
    // It use a hash map to store all the prefix sum and each time check if the any subarray sum to the target
    // Time: O(n), it's a One-Pass method !!! 
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  //Default sum = 0 has one count
        return backtrack(root, 0, sum, map); 
    }
    //BackTrack - one pass
    public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
        if(root == null)
            return 0;
        sum += root.val;
        int res = map.getOrDefault(sum - target, 0);    //See if there is a subarray sum equals to target
        map.put(sum, map.getOrDefault(sum, 0)+1);
        //Extend to left and right child
        res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
        map.put(sum, map.get(sum)-1);   //Remove the current node so it wont affect other path
        return res;
    }

    
}

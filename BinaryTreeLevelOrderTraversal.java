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
    
    // DFS Recursion，很巧妙！
    // Ref: https://discuss.leetcode.com/topic/47255/java-1ms-dfs-recursive-solution-and-2ms-bfs-iterative-solution
    // 不是“先从上到下，再从左到右的”一行一行填，而是“先从左到右，再从上到下”的一列一列填！！！
    // 每一行即每一level的ArrayList都是从最左边开始先开个头，然后逐步向右填满
    // 在过程中每个ArrayList都是不完整的！！而最后大家都变得完整
    // 很巧妙！
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        
        recursionDFS(root, 0, result);
        return result;
    }
    private void recursionDFS(TreeNode curRoot, int curLevel, ArrayList<List<Integer>> result)
    {
        if (curRoot == null)
            return;
        
        // 最精妙在下面的这一句了！！！
        if (curLevel >= result.size())
        {
            ArrayList<Integer> valuesInCurLevel = new ArrayList<>();
            valuesInCurLevel.add(curRoot.val);
            result.add(valuesInCurLevel); // 此时valuesInCurLevel这个ArrayList还不完整，但不要紧，后面会补齐
        }
        else
            result.get(curLevel).add(curRoot.val); // ！！
        
        recursionDFS(curRoot.left, curLevel+1, result);
        recursionDFS(curRoot.right, curLevel+1, result);
    }
    
}

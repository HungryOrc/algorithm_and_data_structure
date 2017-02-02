/* Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
A valid path is from root node to any of the leaf nodes.

Example: Given a binary tree, and target = 5:
     1
    / \
   2   4
  / \
 2   3
Return:
[
  [1, 2, 2],
  [1, 4]
]

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * } */

public class Solution {
    /* @param root the root of binary tree
     * @param target an integer
     * @return all valid paths */
    
    // 方法1: Recursion - Traversal，速度挺快的
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        ArrayList<Integer> curPath = new ArrayList<>();
        curPath.add(root.val);
        findPaths(root, curPath, root.val, target, result);
        return result;
    }
    private void findPaths(TreeNode curNode, 
                           ArrayList<Integer> curPath, 
                           int curSum,
                           int target,
                           List<List<Integer>> result) {
        // if this is a leaf
        if (curNode.left == null && curNode.right == null) {
            if (curSum == target) {
                // 特别注意！这里要 new 一个 ArrayList！！
                result.add(new ArrayList<>(curPath));
            }
            return;
        }          
        
        if (curNode.left != null) {
            curPath.add(curNode.left.val);
            findPaths(curNode.left, curPath, curSum + curNode.left.val, target, result);
            curPath.remove(curPath.size() - 1);
        }
        if (curNode.right != null) {
            curPath.add(curNode.right.val);
            findPaths(curNode.right, curPath, curSum + curNode.right.val, target, result);
            curPath.remove(curPath.size() - 1);
        }
    }
    
    
    // 方法2: Iteration，用了3个Stack。速度挺慢的
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        Stack<ArrayList<Integer>> pathStack = new Stack<>();
        
        nodeStack.push(root);
        sumStack.push(root.val);
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(root.val);
        pathStack.push(newList);
        
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            int curSum = sumStack.pop();
            ArrayList<Integer> curList = pathStack.pop();
            
            if (curSum == target && curNode.left == null && curNode.right == null) {
                result.add(curList);
            }
            
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                sumStack.push(curSum + curNode.left.val);
                ArrayList<Integer> newLeftList = new ArrayList<>(curList);
                newLeftList.add(curNode.left.val);
                pathStack.push(newLeftList);
            }
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                sumStack.push(curSum + curNode.right.val);
                ArrayList<Integer> newRightList = new ArrayList<>(curList);
                newRightList.add(curNode.right.val);
                pathStack.push(newRightList);
            }
        }
        return result;
    }
    
}

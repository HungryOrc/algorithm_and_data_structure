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
    
    // Recursion，速度挺快的
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
  

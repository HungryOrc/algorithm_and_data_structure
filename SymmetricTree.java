/* Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    
    // Recursion。自己想出来的。递归地查：（左，右）、（左的左，右的右）、（左的右，右的左）、（左的左的左，右的右的右）……
    public boolean isSymmetric(TreeNode root) {
        
        if (root == null)
            return true;
            
        return judgeTwoSides(root.left, root.right);
    }
    private boolean judgeTwoSides(TreeNode leftRoot, TreeNode rightRoot)
    {
        if (leftRoot == null && rightRoot == null)
            return true;
        else if (leftRoot == null || rightRoot == null)
            return false;
        else if (leftRoot.val != rightRoot.val)
            return false;
        else 
        {
            return (judgeTwoSides(leftRoot.left, rightRoot.right) &&
                    judgeTwoSides(leftRoot.right, rightRoot.left));
        }
    }
    
    
    // Iteration。自己想出来的。用两个栈，分别装整个大树的左半部分和右半部分
    // 特别注意的是，两个栈的push次序要对应：
    // 左栈push左左时，右栈push右右；左栈push左右时，右栈push右左
    public boolean isSymmetric(TreeNode root) {
        
        if (root == null)
            return true;
        else if (root.left == null && root.right == null)
            return true;
        else if ((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return false;
        else
        {
            Stack<TreeNode> leftSubTree = new Stack<>();
            Stack<TreeNode> rightSubTree = new Stack<>();
            leftSubTree.push(root.left);
            rightSubTree.push(root.right);
            
            while (!leftSubTree.isEmpty() && !rightSubTree.isEmpty())
            {
                TreeNode curLeftNode = leftSubTree.pop();
                TreeNode curRightNode = rightSubTree.pop();
                
                if (curLeftNode.val != curRightNode.val)
                    return false;
                
                if ((curLeftNode.left != null && curRightNode.right == null) ||
                    (curLeftNode.left == null && curRightNode.right != null) ||
                    (curLeftNode.right != null && curRightNode.left == null) ||
                    (curLeftNode.right == null && curRightNode.left != null))
                    return false;
                
                if (curLeftNode.left != null) // it implies curRightNode.right != null
                {
                    // “左左”与“右右”在同一批次分别推入两个栈
                    leftSubTree.push(curLeftNode.left);
                    rightSubTree.push(curRightNode.right);
                }
                if (curLeftNode.right != null) // it implies curRightNode.left != null
                {
                    // “左右”与“右左”在同一批次分别推入两个栈
                    leftSubTree.push(curLeftNode.right);
                    rightSubTree.push(curRightNode.left);
                }
            }        
            return true;
        }
    }   
}

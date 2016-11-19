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
        else if ((leftRoot == null && rightRoot != null) || (leftRoot != null && rightRoot == null))
            return false;
        else if (leftRoot.val != rightRoot.val)
            return false;
        else 
        {
            return (judgeTwoSides(leftRoot.left, rightRoot.right) &&
                    judgeTwoSides(leftRoot.right, rightRoot.left));
        }
    }
    
    
}


   

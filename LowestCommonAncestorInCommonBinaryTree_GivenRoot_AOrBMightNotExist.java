/* Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Return null if LCA does not exist.
Notice: node A or node B may not exist in tree.

Example:
For the following binary tree:
  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4
LCA(5, 6) = 7
LCA(6, 7) = 7

* Definition of TreeNode:
* public class TreeNode {
*     public int val;
*     public TreeNode left, right;
*     public TreeNode(int val) {
*         this.val = val;
*         this.left = this.right = null;
*     }
* } */

// 比较难，我没想到
// 基于 Common Binary Tree 的 LCA 问题的基本形式，即 A 和 B 一定存在的情况。
// 这一题的特点是，A 与 B 都有可能并不存在
// Ref: http://www.jiuzhang.com/solutions/lowest-common-ancestor-iii/
class ResultType {
    public boolean aExist, bExist;
    public TreeNode node;
    ResultType (boolean a, boolean b, TreeNode n) {
        aExist = a;
        bExist = b;
        node = n;
    }
}
    
public class Solution {
  
    // Divide and Conquer - Recursion
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType result = checkLCA(root, A, B);
        if (result.aExist && result.bExist) {
            return result.node;
        } else {
            return null;
        }
    }
    
    private ResultType checkLCA(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultType(false, false, null);
        }
        
        // Divide，考察左右子树的情况
        ResultType leftResult = checkLCA(root.left, A, B);
        ResultType rightResult = checkLCA(root.right, A, B);
        
        // 综合得到当前root以下(包含root本身)的整个树的情况
        boolean aExist = leftResult.aExist || rightResult.aExist || root == A;
        boolean bExist = leftResult.bExist || rightResult.bExist || root == B;
        
        if (root == A || root == B) {
            return new ResultType(aExist, bExist, root);
        }
        
        if (leftResult.node != null && rightResult.node != null) {
            return new ResultType(aExist, bExist, root);
        } else if (leftResult.node != null && rightResult.node == null) {
            return new ResultType(aExist, bExist, leftResult.node);
        } else if (leftResult.node == null && rightResult.node != null) {
            return new ResultType(aExist, bExist, rightResult.node);
        } else { // left.node == null && right.node == null
            return new ResultType(aExist, bExist, null);
        }    
    }
}

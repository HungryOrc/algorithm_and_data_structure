/* Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
Notice:
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with minimum sum and the given binary tree is not an empty tree.

Example:
Given a binary tree:
     1
   /   \
 -5     2
 / \   /  \
0   2 -4  -5 
return the node 1.

* Definition of TreeNode:
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
     * @return the root of the minimum subtree */
    
    // 方法1: Traversal + Divide&Conquer
    // Ref: http://www.jiuzhang.com/solutions/minimum-subtree/
    private TreeNode targetRoot = null;
    private int minSubtreeSum = Integer.MAX_VALUE;
    
    public TreeNode findSubtree(TreeNode root) {
        getSubtreeSum(root);
        return targetRoot;
    }
    private int getSubtreeSum(TreeNode curNode) {
        if (curNode == null) {
            return 0;
        }
        int curSubtreeSum = curNode.val + getSubtreeSum(curNode.left) + getSubtreeSum(curNode.right);
        if (curSubtreeSum < minSubtreeSum) {
            minSubtreeSum = curSubtreeSum;
            targetRoot = curNode;
        }
        return curSubtreeSum;
    }
    
     
    // 方法2: 只用 Divide & Conquer 做，怎么做？？？？？
    
    
    
     
}

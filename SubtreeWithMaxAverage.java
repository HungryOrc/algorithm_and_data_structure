/* Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
Notice:
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum average.

Example:
Given a binary tree:
     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2 
return the node 11.

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
     * @return the root of the maximum average of subtree */
    
    // 方法1: 自定义 result class + Divide&Conquer + Traversal
    class SumAndSizeOfSubtree {
        public int sum;
        public int size;
        public SumAndSizeOfSubtree (int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }
    
    private TreeNode targetSubtree = null;
    private SumAndSizeOfSubtree targetSubtreeStatus = null;
    
    public TreeNode findSubtree2(TreeNode root) {
        getSumAndSize(root);
        return targetSubtree;
    }
    private SumAndSizeOfSubtree getSumAndSize(TreeNode curNode) {
        // step 1: manage null
        if (curNode == null) {
            return new SumAndSizeOfSubtree(0, 0);
        }
        
        // step 2: get left and right branch status
        SumAndSizeOfSubtree leftSubtreeStatus = getSumAndSize(curNode.left);
        SumAndSizeOfSubtree rightSubtreeStatus = getSumAndSize(curNode.right);
        
        // step 3: according to left and right branch, get the current status
        SumAndSizeOfSubtree thisSubtreeStatus = new SumAndSizeOfSubtree (
            leftSubtreeStatus.sum + rightSubtreeStatus.sum + curNode.val,
            leftSubtreeStatus.size + rightSubtreeStatus.size + 1);
        
        // step 4: check if the current status meets our criterion
        if (targetSubtree == null // for the 1st time condition
            || (float)thisSubtreeStatus.sum / (float)thisSubtreeStatus.size > 
               (float)targetSubtreeStatus.sum / (float)targetSubtreeStatus.size) {
            targetSubtree = curNode;
            targetSubtreeStatus = thisSubtreeStatus;
        }
        return thisSubtreeStatus;
    }
    
    
    
}

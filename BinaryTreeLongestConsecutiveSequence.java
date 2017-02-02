/* Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

Example:
For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

* Definition for a binary tree node.
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode(int x) { val = x; }
* } */

// 以下3个方法都出自：
// Ref: http://www.jiuzhang.com/solutions/binary-tree-longest-consecutive-sequence/

public class Solution {
    /* @param root the root of binary tree
     * @return the length of the longest consecutive sequence path */
    
    private int longestConsecutiveLength;
    
    public int longestConsecutive(TreeNode root) {
        longestConsecutiveLength = 0;
        getLCL(root);
        return longestConsecutiveLength;
    }
    // LCL: longest consecutive length
    private int getLCL(TreeNode curNode) {
        // step1: deal with null case, for every recursion
        if (curNode == null) {
            return 0;
        }
        
        // step2: get the status of left and right subtree
        int leftSubtreeLCL = getLCL(curNode.left);
        int rightSubtreeLCL = getLCL(curNode.right);
        
        // step3: update the status of current node, 
        // based on the status of left and right subtree
        int curSubtreeLCL = 1; // at least we have the current node
        if (curNode.left != null && curNode.left.val == curNode.val + 1) {
            curSubtreeLCL = Math.max(curSubtreeLCL, leftSubtreeLCL + 1);
        }
        if (curNode.right != null && curNode.right.val == curNode.val + 1) {
            curSubtreeLCL = Math.max(curSubtreeLCL, rightSubtreeLCL + 1);
        }
        
        // step4: compare the status of the current subtree to the overall max value
        if (curSubtreeLCL > longestConsecutiveLength) {
            longestConsecutiveLength = curSubtreeLCL;
        }
        
        // step5: return the status of the current subtree
        return curSubtreeLCL;
    }
    
} 

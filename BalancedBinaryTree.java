/* Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which 
the depth of the two subtrees of every node never differ by more than 1.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */
 
// Ref: https://discuss.leetcode.com/topic/7798/the-bottom-up-o-n-solution-would-be-better
public class Solution {
    
    // 方法1: Bottom Up Approach 自下而上. 这是最好的方法 ！！！ 时间 O(n)
    /* This method is based on DFS Recursion. 
     Instead of calling depth() explicitly for EACH child node, we return the height of the current node in DFS recursion. 
     When the sub tree of the current node (inclusive) is balanced, the function dfsHeight() returns a non-negative value as the height.
     Otherwise -1 is returned. 
     According to the leftHeight and rightHeight of the two children, 
     the parent node could check if the sub tree is balanced, and decides its return value.
    
     In this bottom up approach, EACH node in the tree only need to be accessed ONCE ! ! ! ! !
     Since any -1 in any level of the tree will result in ALL the ABOVE levels in that path to be -1 ! ! ! ! !
     Thus the time complexity is O(N), much better than the Top Down Solution. */
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }
 
    private int dfsHeight(TreeNode root) {
        if (root == null)
            return 0;
        
        int leftHeight = dfsHeight(root.left);
        int rightHeight = dfsHeight(root.right);
     
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
     
        return Math.max(leftHeight, rightHeight) + 1;
    }    
 
 
    // 方法1: Top Down Approach 自上而下。这里用到一个Recursive函数调用另一个Recursive函数。时间 O(n^2)
    /* checks whether the tree is balanced strictly according to the definition of balanced binary tree: 
     the difference between the heights of the two sub trees are not bigger than 1, 
     and both the left sub tree and right sub tree are also balanced.
     For the current node root, calling depth() for its left and right children actually has to access all of its children, 
     thus the complexity is O(n). 
     We do this for each node in the tree, so the overall complexity of isBalanced will be O(n^2). */
 
    // 这个函数 recursively call 自己
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        
        if (Math.abs(leftDepth - rightDepth) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;
        else
            return false;
    }
 
    // 这个helper 函数也 recursively call 自己！！
    private int getDepth (TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }   
    
 
    // 方法3: 自定义新的 return data type
    // Ref: http://www.jiuzhang.com/solutions/balanced-binary-tree/
    class BalanceAndDepth {
        public boolean isBalanced;
        public int maxDepth;
        public BalanceAndDepth (boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        return checkBalanceAndDepth(root).isBalanced;
    }
    private BalanceAndDepth checkBalanceAndDepth(TreeNode curNode) {
        if (curNode == null) {
            return new BalanceAndDepth(true, 0);
        }
        
        BalanceAndDepth leftSubtreeStatus = checkBalanceAndDepth(curNode.left);
        BalanceAndDepth rightSubtreeStatus = checkBalanceAndDepth(curNode.right);
        
        // if either subtree is not balanced
        if (!leftSubtreeStatus.isBalanced || !rightSubtreeStatus.isBalanced) {
            return new BalanceAndDepth(false, -1); // the actual depth does not matter now
        }
        
        // if the current node itself is not balanced
        if (Math.abs(leftSubtreeStatus.maxDepth - rightSubtreeStatus.maxDepth) > 1) {
            return new BalanceAndDepth(false, -1); // the actual depth does not matter now
        }
        
        return new BalanceAndDepth(true, 
            Math.max(leftSubtreeStatus.maxDepth, rightSubtreeStatus.maxDepth) + 1);
    }
}

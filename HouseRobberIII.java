/* The thief has found himself a new place for his thievery again. 
There is only one entrance to this area, called the "root." Besides the root, 
each house has one and only one parent house. After a tour, 
the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.
Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

// 这一篇的几个答案都很不错！！
// Ref: https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem

public class Solution
{
    // 认真思考就能发现，其实最终答案要么是第0（即root）+ 2 + 4 + 6 + 8... 层之和
    // 要么是第 1 + 3 + 5 + 7... 层之和。这二者中的一个
     // 以下这个 recursion 方法，直观，代码简明，但速度很慢
    public int rob(TreeNode root) 
    {
        if (root == null) return 0;
        
        int curNode_AndGrandChildren_AndOneLevelInEveryTwoLevels = root.val;
        if (root.left != null)
            curNode_AndGrandChildren_AndOneLevelInEveryTwoLevels += 
                rob(root.left.left) + rob(root.left.right);
        if (root.right != null)
            curNode_AndGrandChildren_AndOneLevelInEveryTwoLevels += 
                rob(root.right.left) + rob(root.right.right);
        
        // 其中 rob(root.left) + rob(root.right) 即当前node的两个children 及这两个children
        // 的grandChildren，的grandChildren... 每过一个层级取一个层级，然后这一系列层级的和
        return Math.max(curNode_AndGrandChildren_AndOneLevelInEveryTwoLevels, 
                        rob(root.left) + rob(root.right));
    }
}

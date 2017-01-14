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
// 里面有很长很详细的思维过程，逐步优化算法的来龙去脉，没有抄过来，可以去这个链接看

public class Solution
{
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

        return Math.max(curNode_AndGrandChildren_AndOneLevelInEveryTwoLevels, 
                        rob(root.left) + rob(root.right));
     }
     
     
     // 改进上面算法后的算法
     // 用一个HashMap把每个node及以它为root的subtree的max value都存下来
     // 这样就减免了巨量的重复运算，大幅提高了速度
     public int rob(TreeNode root) 
     {
        HashMap<TreeNode,Integer> nodeMap = new HashMap<>();
        return robSub(root, nodeMap);
     }
     private int robSub(TreeNode root, Map<TreeNode,Integer> map) 
     {
        if (root == null) 
            return 0;

        if (map.containsKey(root)) 
            return map.get(root);

        int val = 0;
        if (root.left != null)
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        if (root.right != null)
            val += robSub(root.right.left, map) + robSub(root.right.right, map);

        val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);

        return val;
     }
     
     
     // 进一步改进为一个 Greedy Algorithm
     // 没有细看，回头看上面链接里对于这个方法的详细解释吧
     public int rob(TreeNode root) {
         int[] res = robSub(root);
         return Math.max(res[0], res[1]);
     }
     private int[] robSub(TreeNode root) {
         if (root == null) return new int[2];

         int[] left = robSub(root.left);
         int[] right = robSub(root.right);
         int[] res = new int[2];

         res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
         res[1] = root.val + left[0] + right[0];

         return res;
     }
}

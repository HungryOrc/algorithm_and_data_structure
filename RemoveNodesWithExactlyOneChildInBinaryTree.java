/* 比如，下面这个树，remove 所有的有且只有一个child的nodes以后，得到右边那个树。
注意，有些node只有一个child，我们只remove它本人就可以了，它的儿子或者孙子或者曾孙不是一并被抛弃，是另外一个问题，
要看它的儿孙们是否各自只有一个child，所以要逐个分开地处理
         2                   2
     /      \               / \
    7        5      =>     6   4
    \        /            / \
     6      9            1  11
    / \    /
   1  11   4
  
这一题的思路与 Remove Nodes Outside A Range In Binary Search Tree 是同源的 ！！！ 看一下那一题的思路分析 ！！！  */


// 代码虽然短，但思路非常精妙 ！！！ 要好好理解 ！！！

public class Solution {
    
    public TreeNode removeNodesWithExactlyOneChild(TreeNode root) {
        // 这不光是为了 corner case的处理！ 到了叶子以下的时候，也靠了这一句
        if (root == null) {
            return null; 
        }
        
        // 诀窍 ！！！ 先落实好本node在整个变故发生以后的左右子 ！！！
        // 并且在此就直接把它们连到root上去 ！！！
        root.left = removeNodesWithExactlyOneChild(root.left);
        root.right = removeNodesWithExactlyOneChild(root.right);
        
        if (root.left == null && root.right == null) {
            return root;
        } else if (root.left != null) { // && root.right == null
            return root.left;
        } else if (root.right != null) { // && root.left == null
            return root.right;
        } else { // 左右子after management 都不是 null
            return root;
        }
    }
}

// 参考在 binary tree 里找 2个一定存在的Nodes的 最低公共祖先 的方法
// Laioffer 的解法

class TreeNode {
    int val;
    List<TreeNode> children;
}

public class Solution {

    public TreeNode LCA_2Nodes_KBranchTree(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        }
        if (root == one || root == two) {
            return root;
        }
    
        // 注意 ！！！ 这个计数不是global的，而只是这一层的。这个计数不会往上一层再传递。
        // 但在上一层的处理里，每一个不为null的child，自然会再次给上一层那里的计数+1，
        // 所以，每一层的计数，它的肉体并不会升华，但它的精神会永远一代代被继承下去
        int numOfNodesFound = 0;
        TreeNode tmpOutcome = null;
        
        for (TreeNode child : root.children) {
            TreeNode outcomeFromThisChild = LCA_2Nodes_KBranchTree(child, one, two);
            
            if (outcomeFromThisChild != null) {
                numOfNodesFound ++;
            
                // 精华在这一句 ！！！
                // 如果有所斩获的child为2个，则表示当前root一定就是最终的答案。返回它就可以了
                // 当然，这个返回不会完全终止整个程序，而是返回给当前root的parent，然后一层一层往上返，一直到整个树的总root，才会结束程序
                if (numOfNodesFound == 2) {
                    return root;
                } else { // numOfNodesFound == 1
                    tmpOutcome = tmpOutcome;
                } 
            }
        }
        return tmpOutcome;
    }
}

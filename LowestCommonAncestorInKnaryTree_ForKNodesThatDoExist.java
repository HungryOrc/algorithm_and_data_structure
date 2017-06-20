// 参考在 K-nary tree 里找 2个一定存在的Nodes的 最低公共祖先 的方法
// 以及 在 Binary tree 里找 k个一定存在的Nodes的 最低公共祖先 的方法
// Laioffer 的解法

class TreeNode {
    int val;
    List<TreeNode> children;
}

public class Solution {

    public TreeNode LCA_KNodes_KBranchTree(TreeNode root, HashSet<TreeNode> targets) {
        if (root == null) {
            return null;
        }
        if (targets.contains(root)) {
            return root;
        }
    
        int numOfNodesFound = 0;
        TreeNode tmpOutcome = null;
        
        for (TreeNode child : root.children) {
            TreeNode outcomeFromThisChild = LCA_KNodes_KBranchTree(child, targets);
            
            if (outcomeFromThisChild != null) {
                numOfNodesFound ++;
            
                // 精华在这一句 ！！！
                // 如果有所斩获的child超过一个，即至少2个，则表示当前root一定是某个层次上的小头目，它下面
                // 一定有2个或者n个我们要找的target nodes，但到底是几个，我们不用关心了！！！ 因为题目规定，
                // 所有k个nodes都一定存在在这个树里 ！！！ 
                // 到了这里，我们已经可以在这个层次上放心大胆地返回这个root了 ！！！ 其他的都不用care了 ！！！ 哦也 ！！！
                if (numOfNodesFound > 1) {
                    return root;
                } else { // numOfNodesFound == 1
                    tmpOutcome = outcomeFromThisChild;
                } 
            }
        }
        return tmpOutcome;
    }
}

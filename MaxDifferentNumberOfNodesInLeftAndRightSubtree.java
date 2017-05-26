// 找一个树里，所有的nodes中，左子树和右子树分别含有的总nodes数的差异最大的那个node。返回这个node

/* 特别注意 ！！！ 这一题的思维方式的精华在于：
   下面的 helper function，它返回的值，只是它实际做的工作的一部分 ！！！ 而且不是最关键的那一部分 ！！！
   它返回的（返回给它的 parent node），是以当前node为root的子树所含有的总nodes的个数，含本node在内
   它所做的工作，还包含了：
   命令它的左右子树进行helper function的操作、比较左右子树的nodes差额、将本差额与全局max相比、更新全局max及最终要求的node */

public class Solution {

    TreeNode resultNode;
    int maxDiff;
    
    public TreeNode findNodeWithMaxDiff(TreeNode root) {
        resultNode = root;
        maxDiff = 0;
        
        countNodesInSubtrees_AndUpdateResults(root);
        return maxDiff;
    }

    // 一个两面三刀的helper function：它所返回的，只是它所做过的事情的一小部分……
    private int countNodesInSubtrees_AndUpdateResults(TreeNode node) {
        if (node == null) {
            return 0;
        }   
        
        int numberOfNodesInLeftSubtree = countNodesInSubtrees_AndUpdateResults(node.left);
        int numberOfNodesInRightSubtree = countNodesInSubtrees_AndUpdateResults(node.right);
        
        int diff = Math.abs(numberOfNodesInLeftSubtree - numberOfNodesInRightSubtree);
        if (diff > maxDiff) {
            maxDiff = diff;
            resultNode = node;
        }
        
        // 返回（给本node的 parent node）以本node为root的子树所含有的总nodes的个数，含本node在内
        return numberOfNodesInLeftSubtree + numberOfNodesInRightSubtree + 1;
    }
}

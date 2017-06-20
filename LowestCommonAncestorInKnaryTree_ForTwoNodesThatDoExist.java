// 参考在 binary tree 里找 2个一定存在的Nodes的 最低公共祖先 的方法
// Laioffer 的解法

public class Solution {

    public TreeNode LCA_2Nodes_KBranchTree(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        }
        if (root == one || root == two) {
            return root;
        }
    
        int numOfNodesFound = 0;
        TreeNode tmpOutcome = null;
        
        for (TreeNode child : root.children) {
            TreeNode outcomeFromThisChild = LCA_2Nodes_KBranchTree(child, one, two);
            
            if (outcomeFromThisChild != null) {
                numOfNodesFound ++;
            
                if (numOfNodesFound == 2) {
                    return root;
                } else { // numOfNodesFound == 1
                    tmpOutcome = child;
                } 
            }
        }
        return tmpOutcome;
    }
}

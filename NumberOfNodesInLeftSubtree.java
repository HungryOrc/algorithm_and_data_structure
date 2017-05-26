// 找到每个node的左子树里的总nodes个数，并存在本node的相应field里

class TreeNode {
    TreeNode left, right;
    int value;
    int numOfNodesInLeftSubtree;
}

public class Solution {

    public void findNumberOfNodesInLeftSubtree(TreeNode root) {
        countNodes(root);
    }

    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);
        
        node.numOfNodesInLeftSubtree = leftCount;
        
        return leftCount + rightCount + 1; // 向本node的父节点返回的信息 ！！！
    }
}

/* Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.
If the given node has no in-order successor in the tree, return null.
It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p).
对于BST，in-order其实就是从小到大排列。所以 in order successor 其实就是比指定node大一位的node

Example
Given tree = [2,1] and node = 1:
  2
 /
1
return node 2.

Given tree = [2,1,3] and node = 2:
  2
 / \
1   3
return node 3.

Challenge 
O(h), where h is the height of the BST.

/* Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */






// 方法2：我的笨办法
public class Solution {
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        
        if (root == null || p == null) {
            return null;
        }
        
        // 如果p有右子
        if (p.right != null) {
            TreeNode pRight = p.right;
            // 如果p的右子没有左子，那么p的右子就是p的successor
            if (pRight.left == null) {
                return pRight;
            // 如果p的右子有左子，那么顺着这个左向下到头，就是p的successor
            } else {
                TreeNode pRightLeft = pRight.left;
                while(pRightLeft.left != null) {
                    pRightLeft = pRightLeft.left;
                }
                return pRightLeft;
            }
        }
        
        // 如果p没有右子，且它本身就是root，则再不可能有比她更大的node了
        else if (p == root) {
            return null;
        }
        
        // 如果p没有右子，且它不是root
        else {
            TreeNode parent = getParent(p, root);
            // 注意！！！parent的val未必大于p的val！！！因为p可能是parent的右子！！！
            // 这样就要不停地上溯p的爷爷，曾爷爷。。。
            while (parent.val <= p.val && parent != root) {
                parent = getParent(parent, root);
            }
            
            if (parent.val > p.val) {
                return parent;
            // 在这个树里，比p大的node不存在，比如p就是从root开始一路向右下来的
            } else { 
                return null;
            }
        }
    }
    
    private TreeNode getParent(TreeNode child, TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        TreeNode curNode = root;
        nodeQueue.offer(curNode);
        
        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();
            if (curNode.left == child || curNode.right == child) {
                return curNode;
            }
            
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
            }
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
            }
        }
        return null;
    }
}

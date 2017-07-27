/* 所谓的 Perfect Binary Tree 就是中文的 “完全树”，就是所有的叶子节点全部填满，整棵树每一条路径都一样height的树。如下：

                      1
                    /   \
                   2     3
                  / \   / \     
                 4   5 6   7

现在要求对它进行一种特殊的遍历：第一要求从下往上，第二要求从两边到中间。具体为：
4, 7, 5, 6, 2, 3, 1  
时间：O(n) + O(n/2) + O(n/4) + ... = O(2n)   */


// 思路：还没有彻底理解透彻 ？？？？？？
public class Solution {
    
    public void printPerfectBinaryTree(TreeNode root) {
        int totalHeight = getHeight(root);
        
        // curLevel > 1 即只做到从上往下的第二行，第一行即整棵树的root在最后单另做 
        for (int curLevel = totalHeight; curLevel > 1; curLevel --) {
            printCurLevel(root.left, root.right, curLevel, 2);
        }
        
        System.out.print(root.val);
        System.out.println();
    }
    
    private void printCurLevel(TreeNode nodeL, TreeNode nodeR, int curLevel, int startLevel) {
        if (curLevel == startLevel) {
            System.out.print(nodeL.val + " " + nodeR.val);
            System.out.println();
            return;
        } else if (curLevel < startLevel) {
            return;
        }
        
        if (nodeL.left != null && nodeR.right != null) {
            printCurLevel(nodeL.left, nodeR.right, curLevel, startLevel + 1);
        }
        if (nodeL.right != null && nodeR.left != null) {
            printCurLevel(nodeL.right, nodeR.left, curLevel, startLevel + 1);
        }
    }
    
    private int getHeight(TreeNode curNode) {
        if (curNode == null) {
            return 0;
        }
        return Math.max(getHeight(curNode.left), getHeight(curNode.right)) + 1;
    }
}

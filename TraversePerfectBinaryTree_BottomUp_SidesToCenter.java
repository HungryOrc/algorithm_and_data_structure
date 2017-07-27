/* 所谓的 Perfect Binary Tree 就是中文的 “完全树”，就是所有的叶子节点全部填满，整棵树每一条路径都一样height的树。如下：

                      1
                    /   \
                   2     3
                  / \   / \     
                 4   5 6   7

现在要求对它进行一种特殊的遍历：第一要求从下往上，第二要求从两边到中间。具体为：
4, 7, 5, 6, 2, 3, 1  
时间：O(n) + O(n/2) + O(n/4) + ... = O(2n)   */


// 思路非常巧妙 ！！！ 要用心体会 ！！！
public class Solution {
    
    public void printPerfectBinaryTree(TreeNode root) {
        int totalHeight = getHeight(root);
        
        // curLevel > 1 即只做到从上往下的第二行，第一行即整棵树的root在最后单另做 
        // 下面的for loop是精华 ！！！
        for (int printLevel = totalHeight; printLevel > 1; printLevel --) {
            printCurLevel(root.left, root.right, printLevel, 2);
        }
        
        System.out.print(root.val);
        System.out.println();
    }
    
    // 精华　！！！
    // printLevel是规定好不变的，这个函数的目的在于，随着curLevel的增大，不断地开枝散叶，
    // 以够着在printLevel上的需要凑成一对来print的那一对又一对nodes，比如上面例子中的 4和7, 5和6
    private void printCurLevel(TreeNode nodeL, TreeNode nodeR, int printLevel, int curLevel) {
        if (printLevel == curLevel) {
            System.out.print(nodeL.val + " " + nodeR.val);
            System.out.println();
            return;
        } else if (printLevel < curLevel) {
            return;
        }
        
        // 向下走且往两边走的方向
        if (nodeL.left != null && nodeR.right != null) {
            printCurLevel(nodeL.left, nodeR.right, printLevel, curLevel + 1);
        }
        // 向下走且往中间走的方向
        if (nodeL.right != null && nodeR.left != null) {
            printCurLevel(nodeL.right, nodeR.left, printLevel, curLevel + 1);
        }
    }
    
    private int getHeight(TreeNode curNode) {
        if (curNode == null) {
            return 0;
        }
        return Math.max(getHeight(curNode.left), getHeight(curNode.right)) + 1;
    }
}

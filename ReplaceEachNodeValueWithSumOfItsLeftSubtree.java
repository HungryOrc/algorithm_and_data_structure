/* 要求的效果：

            1                             1                             1                             70
       /         \                   /         \                   /         \                    /         \
      2           3                 2           3                 25          17                 25          17
    /   \       /   \     =>      /   \       /   \     =>      /   \       /   \       =>     /   \       /   \ 
   4     5     6     7           8     10    6     7           8     10    6     7            8     10    6     7
  / \   /       \               / \   /       \               / \   /       \                / \   /       \
 8   9  10      11             8   9  10      11             8   9  10      11              8   9  10      11

解决的思路：
* 先replace左右子 ！！！（右子也得replace！！！） 再加和左子树，以replace自己 ！！！
* 计算实际上是从底层往上层，但写代码是从上层往下逐层到底层 ！！！

详细解释：
1. 每个node，要replace它的值，就必须先replace它的左子树里所有应该被replace的nodes的值
2. 注意，每个node的右子树里的值，虽然与本node的值的replace无关，但右子树里也是存在一些nodes，它们的值必须被replace的！！！
   在这整棵树里，任何一个node，只要它有非null的左子树，它的值就必须被replace！！！
3. 从底层往上，每一层该replace value的nodes都被处理以后，再通过加和，来replace上一层的那些应该被replace value的nodes
   注意！！！加和的时候，右子不算，但左子以下的所有左孙和右孙，左重孙和右重孙，是都要算入的 ！！！ */

import java.util.*;

class TreeNode {
    public int value;
    public TreeNode left, right;
    public TreeNode(int val) {
	this.value = val;
    }
}

public class Solution {
	
    public void replaceValueWithSumOfLeftSubtree(TreeNode root) {
        updateValueOfCurNode(root);
    }

    // 先replace左右子 ！！！ 再加和，以replace自己 ！！！
    private void replaceValueOfCurNode(TreeNode node) {
        if (node == null) {
            return;
        }
        
        replaceValueOfCurNode(node.left);
        replaceValueOfCurNode(node.right);
        
        // 注意 ！！！用当前 node的左子树里的所有nodes之和 来 replace当前node的value 之前，
	// 必须先判断：当前 node 的左child 是否为 null ！！！否则会出错：
        // cur node的值会被null left child 更新成 0 ！！！
        if (node.left != null) {
            node.value = sumSubtree(node.left);
        }
    }

    private int sumSubtree(TreeNode node) {
        if (node == null) {
            return 0;   
        }
        return node.value + sumSubtree(node.left) + sumSubtree(node.right);
    }
    
    // test
    public static void main(String[] args) {
	// construct the tree
	TreeNode n1 = new TreeNode(1);
	TreeNode n2 = new TreeNode(2);
	TreeNode n3 = new TreeNode(3);
	TreeNode n4 = new TreeNode(4);
	TreeNode n5 = new TreeNode(5);
	TreeNode n6 = new TreeNode(6);
	TreeNode n7 = new TreeNode(7);
	TreeNode n8 = new TreeNode(8);
	TreeNode n9 = new TreeNode(9);
	TreeNode n10 = new TreeNode(10);
	TreeNode n11 = new TreeNode(11);
	n1.left = n2;
	n1.right = n3;
	n2.left = n4;
	n2.right = n5;
	n3.left = n6;
	n3.right = n7;
	n4.left = n8;
	n4.right = n9;
	n5.left = n10;
	n6.right = n11;

	Solution myS = new Solution();
	myS.replaceValueWithSumOfLeftSubtree(n1);

	Queue<TreeNode> nodeQueue = new LinkedList<>();
	nodeQueue.offer(n1);
	    
	while (!nodeQueue.isEmpty()) {
	    int curLayerSize = nodeQueue.size();
	    for (int i = 0; i < curLayerSize; i++) {
	 	TreeNode curNode = nodeQueue.poll();
		System.out.print(curNode.value + " ");
		if (curNode.left != null) {
		    nodeQueue.offer(curNode.left);
		}
		if (curNode.right != null) {
		    nodeQueue.offer(curNode.right);
		}
	    }
	    System.out.println();
        }
    }
}

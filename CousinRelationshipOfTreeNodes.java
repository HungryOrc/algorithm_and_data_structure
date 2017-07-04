/* 判断2个TreeNode之间是否为Cousin关系，返回boolean值。是Cousin关系的充要条件有两个：
1. 从整个树的root开始算，它们处于同一个level
2. 它们的parent不是同一个node

这一题是给root的。另外会给要找的2个nodes。假设：
1. The tree is not null.
2. The 2 nodes do exist in the binary tree whose root is the given root.  */


// 方法1：Laioffer很巧妙的方法！！！





// 方法2：我用了一个 custom class 来wrap每一个TreeNode的两种信息：parent和level
// Time: O(n), n is the number of nodes in the tree. Since we have to walk through each node in the tree.
// Space: O(height of tree), since the level of call stacks would be at most the height of the tree, 
// and each call stack uses O(1) extra space.

class ResultType {
	TreeNode parent;
	int level;
	
	public ResultType(TreeNode p, int l) {
		parent = p;
		level = l;
	}
}
 
public class Solution {
 
	public boolean isCousin(TreeNode root, TreeNode one, TreeNode two) {
		TreeNode parent = new TreeNode(Integer.MIN_VALUE);
		parent.right = root;
 
		ResultType rt1 = findNode(root, parent, 1, one);
		ResultType rt2 = findNode(root, parent, 1, two);
	 
		if (rt1.level == rt2.level && rt1.parent != rt2.parent) {
			return true;
		} else {
			return false;
		}
	}
 
	private ResultType findNode(TreeNode root, TreeNode parent, int curLevel, TreeNode target) {	
		
		if (root == null) { // 这意味着在当前这条path上没有找到target node ！！
			return new ResultType(null, Integer.MAX_VALUE);
		}
		 
		if (root == target) {
			return new ResultType(parent, curLevel);
		}	
		 
		ResultType resultL = findNode(root.left, root, curLevel + 1, target);
		ResultType resultR = findNode(root.right, root, curLevel + 1, target);	
		 
		if (resultL.parent == null && resultR.parent == null) {
			return resultL; // = new ResultType(null, Integer.MAX_VALUE);
		} else if (resultL.parent == null) {
			return resultR;
		} else {
			return resultL;
		}
	}
}

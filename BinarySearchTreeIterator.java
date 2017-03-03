/* Design an iterator over a binary search tree with the following rules:
Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.

Challenge: Extra memory usage O(h), h is the height of the tree.
Super Star: Extra memory usage O(1).

Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 
 * Example of iterating a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    // do something to each node
 *    // ...
 * } */

// 方法1：我的方法
public class BSTIterator {
    
    public ArrayList<TreeNode> treeNodeAL;
    int sizeOfAL;
    int curIndex;
    
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        this.treeNodeAL = new ArrayList<TreeNode>();
        // 先放一个dummy node作为所有tree nodes的previous head，
        // 便于iterator进行next操作
        this.treeNodeAL.add(new TreeNode(Integer.MIN_VALUE));
        inorderTraversal(root, this.treeNodeAL);
        
        this.sizeOfAL = this.treeNodeAL.size();
        
        this.curIndex = 0;
    }

    private void inorderTraversal(TreeNode curNode, ArrayList<TreeNode> treeNodeAL) {
        if (curNode == null) {
            return; // do nothing
        }
        
        inorderTraversal(curNode.left, treeNodeAL);
        treeNodeAL.add(curNode);
        inorderTraversal(curNode.right, treeNodeAL);
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        if (this.curIndex == this.sizeOfAL - 1) {
            return false;
        } else {
            return true;
        }
    }
    
    //@return: return next node
    public TreeNode next() {
        this.curIndex ++;
        return this.treeNodeAL.get(curIndex);
    }
}


// 方法2：九章的方法。没看懂？？？？？？？？？
// Ref: http://www.jiuzhang.com/solutions/binary-search-tree-iterator/
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode curt;
    
    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        curt = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return (curt != null || !stack.isEmpty());
    }
    
    //@return: return next node
    public TreeNode next() {
        while (curt != null) {
            stack.push(curt);
            curt = curt.left;
        }
        
        curt = stack.pop();
        TreeNode node = curt;
        curt = curt.right;
        
        return node;
    }
}

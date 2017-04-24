/* Given a binary tree, determine if it is a valid binary search tree (BST).
Assume a BST is defined as follows:
1. The left subtree of a node contains only nodes with keys less than the node's key.
2. The right subtree of a node contains only nodes with keys greater than the node's key.
3. Both the left and right subtrees must also be binary search trees.
4. A single node tree is a BST.

Example: An example:
  2
 / \
1   4
   / \
  3   5
The above binary tree is serialized as {2,1,4,#,#,3,5} (in level order).

* Definition of TreeNode:
* public class TreeNode {
*     public int val;
*     public TreeNode left, right;
*     public TreeNode(int val) {
*         this.val = val;
*         this.left = this.right = null;
*     }
* } */

public class Solution {
    /* @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false */
    
    // 方法1：Divide and Conquor
    // Time: O(n), n is the number of the TreeNodes in the tree. Because we need to walk through
    // every node in the tree in the worst case
    // Space: O(height of the tree), because the number of call stacks equals to the number of tree 
    // height, and each call stack requires constant space
    public boolean isValidBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }  
    
    private boolean isBST(TreeNode node, int min, int max) {
        // 只要是 recursion，都千万记住 base case 千万别忘了 ！！！
        // 要是忘了就是 一发入魂 级别的 instant death ！！！
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) { // 最严格的BST里不允许出现重复的值
            return false;
        }
      
        return (isBST(node.left, min, node.val) && isBST(node.right, node.val, max));
        // 特别注意 ！！！这么做比先得到 leftIsBST 和 rightIsBST，再把两个 boolean && 在一起  好  很  多 ！！！
        // 这是因为，return(left && right)的写法，如果左半边==false，则右半边就不用做了 ！！！
        // 而分别先把left和right搞出来的写法，是一点懒都没法偷的！全部都得先算出来再说！
    }
  
  
    // 方法2: Iteration。一个不含重复元素的BST，被in-order遍历的话，会形成一个单调上升的序列
    // 如此，我们就可用stack做一个中序遍历，把结果放到一个 array list 里，再验证是不是每个元素都比前一个大
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        ArrayList<Integer> allNodesVal_InOrder = new ArrayList<>();
        
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            
            // 当前node是leaf；
            // 或者当前node的左右children都被放入stack了，即当前node之前已被处理过了。
            // 那么当前node就可以被放入list了
            if (curNode.left == null && curNode.right == null) {
                allNodesVal_InOrder.add(curNode.val);
                continue;
            }
            
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
            }
            nodeStack.push(curNode);
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
            }
            
            curNode.left = null;
            curNode.right = null;
        }
        
        for (int i = 1; i < allNodesVal_InOrder.size(); i++) {
            if (allNodesVal_InOrder.get(i) <= allNodesVal_InOrder.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    
    
    // 方法3: Divide and Conquer + Custom Result Class 来做
    // http://www.jiuzhang.com/solutions/validate-binary-search-tree/
    class ResultType {
        boolean is_bst;
        int maxValue, minValue;

        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public class Solution {

        public boolean isValidBST(TreeNode root) {
            ResultType r = validateHelper(root);
            return r.is_bst;
        }
        private ResultType validateHelper(TreeNode root) {
            if (root == null) {
                return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }

            ResultType left = validateHelper(root.left);
            ResultType right = validateHelper(root.right);

            if (!left.is_bst || !right.is_bst) {
                // if is_bst is false then minValue and maxValue are useless
                return new ResultType(false, 0, 0);
            }

            if (root.left != null && left.maxValue >= root.val || 
                  root.right != null && right.minValue <= root.val) {
                return new ResultType(false, 0, 0);
            }

            return new ResultType(true,
                                  Math.max(root.val, right.maxValue),
                                  Math.min(root.val, left.minValue));
        }
    }
  
}

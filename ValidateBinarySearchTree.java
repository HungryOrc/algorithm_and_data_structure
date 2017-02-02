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
    
    // 方法1: Iteration。一个不含重复元素的BST，被in-order遍历的话，会形成一个单调上升的序列
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
    
    
    // 方法2: Recursion。延续上面的原则，BST被in-order traversal的话应该形成一个不断上升的序列
    private int prevVal = Integer.MIN_VALUE;
    private boolean isTheFirstNode = true;
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        // 先左，
        // 看左边是否非BST，如果左边非，则整个也非
        // 然后在左子树里不断更新即不断增大 value of prev node 的值
        if (root.left != null && !isValidBST(root.left)) {
            return false;
        }
        
        // 再中
        if (!isTheFirstNode && root.val <= prevVal) {
            return false;
        }
        prevVal = root.val;
        isTheFirstNode = false;
        
        
        // 最后右
        if (root.right != null && !isValidBST(root.right)) {
            return false;
        }
        
        // 如果一路都没问题，则最后证明是true BST
        return true;
    }
    
    
    // 方法3: Divide and Conquer
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

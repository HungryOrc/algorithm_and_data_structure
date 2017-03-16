/* Given a binary tree, find the maximum path sum from root.
The path may end at any node in the tree and contain at least one node in it.

Example
Given the below binary tree:

  1
 / \
2   3
return 4. (1->3)

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * } */

// 方法1：Recursion - Divide and Conquer
public class Solution {

    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int maxPathSumOfLeftSubtree = maxPathSum2(root.left);
        int maxPathSumOfRightSubtree = maxPathSum2(root.right);
        
        int maxPathSumOfSubtrees = Math.max(maxPathSumOfLeftSubtree, maxPathSumOfRightSubtree);
        if (maxPathSumOfSubtrees > 0) {
            return root.val + maxPathSumOfSubtrees;
        } else { // 如果 <= 0，那么还不如不加下面的子树了，就只有root.val自己反而更大
            return root.val;
        }
    } 
}


// 方法2：Recursion - Traversal
public class Solution {
    int maxPathSum;
    
    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSum = Integer.MIN_VALUE;
        
        findMaxPathSumFromRootToAnyNode(root, root.val);
        return maxPathSum;
    }
    
    private void findMaxPathSumFromRootToAnyNode(TreeNode curNode, int curPathSum) {
        maxPathSum = Math.max(maxPathSum, curPathSum);
        
        if (curNode.left != null) {
            findMaxPathSumFromRootToAnyNode(curNode.left, curPathSum + curNode.left.val);
        }
        if (curNode.right != null) {
            findMaxPathSumFromRootToAnyNode(curNode.right, curPathSum + curNode.right.val);
        }
    }
}


// 方法3：Iteration (即 Non-Recursion)。用2个Stack。或者用2个Queue也是一样的，这个在这里无关紧要
public class Solution {

    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int maxPathSum = Integer.MIN_VALUE;
        
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        Stack<Integer> pathSumStack = new Stack<>();
        pathSumStack.push(root.val);
        
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            int curPathSum = pathSumStack.pop();
            
            maxPathSum = Math.max(maxPathSum, curPathSum);
            
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                pathSumStack.push(curPathSum + curNode.left.val);
            }
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                pathSumStack.push(curPathSum + curNode.right.val);
            }
        }
        return maxPathSum;
    }
}

/* Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target. 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    // iteration。这个是我想出来的
    public int closestValue(TreeNode root, double target) {
        
        int result = root.val;
        TreeNode curNode = root;
        
        while (curNode != null)
        {
            if (curNode.val == target)
                return curNode.val;
            else
            {
                if (Math.abs(curNode.val - target) < Math.abs(result - target))
                    result = curNode.val;
                if (curNode.val > target)
                    curNode = curNode.left;
                else // curNode.val < target
                    curNode = curNode.right;
            }
        }
        return result;
    }

    
    // 我的recursion。要默写！！！
    public int closestValue(TreeNode root, double target) {
        
        int result = root.val;
        
        return findClosest(root, target, result);
    }
    private int findClosest(TreeNode root, double target, int result)
    {
        if (root.val == target)
            return root.val;
            
        else
        {
            if (Math.abs(root.val - target) < Math.abs(result - target))
                result = root.val;
                
            if (root.val > target)
            {
                if (root.left == null)
                    return result;
                else // root.left != null
                    return findClosest(root.left, target, result);
            }
            
            else // root.val < target
            {
                if (root.right == null)
                    return result;
                else // root.right != null
                    return findClosest(root.right, target, result);
            }
        }
    }
    
    
    // 方法3
    // recursion 精粹版。这个不是我想出来的！值得反复研读！
    // 有很多妙处在里面！！
    public int closestValue (TreeNode root, double target)
    {
       int rootValue = root.val;
       if (rootValue == target)
          return rootValue;
       
       TreeNode nextNode = target < rootValue ? root.left : root.right;
       
       if (nextNode == null
          return rootValue;
       int nextValue = closestValue (nextNode, target); // 不带rootValue到下一层去比，直接弄出下一层以下的min然后再回来和rootValue比（下一句）
       return Math.abs(rootValue - target) < Math.abs(nextValue - target) ? rootValue : nextValue;
    }
    
    

    
    
}

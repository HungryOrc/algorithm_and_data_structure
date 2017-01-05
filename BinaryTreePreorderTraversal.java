/* Given a binary tree, return the preorder traversal of its nodes' values. 
先root，再左子树，再右子树。

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

public class Solution 
{
    // 我的 Iteraion 方法
    public List<Integer> preorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
            
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        
        while (!nodeStack.isEmpty())
        {
            TreeNode curNode = nodeStack.pop();
            result.add(curNode.val);
            
            // 因为之后要先处理左子树，所以先把右child放到stack里去，再放左child
            if (curNode.right != null)
                nodeStack.push(curNode.right);
            if (curNode.left != null)
                nodeStack.push(curNode.left);
        }
        return result;
    }
}


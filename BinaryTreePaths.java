/* Given a binary tree, return all root-to-leaf paths.
For example, given the following binary tree:
   1
 /   \
2     3
 \
  5
All root-to-leaf paths are: ["1->2->5", "1->3"]

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

public class Solution {
    
    // Recursion方法，自己写的
    public List<String> binaryTreePaths(TreeNode root) {
        
        ArrayList<String> result = new ArrayList<>();
        if (root == null)
            return result;
        
        String curString = "";
        recordPath(root, curString, result);
        
        return result;
    }
    private void recordPath(TreeNode curRoot, String curString, ArrayList<String> result)
    {
        curString += curRoot.val;
        
        if (curRoot.left == null && curRoot.right == null)
            result.add(curString);
        else
        {
            curString += "->"; // 注意这个加箭头的位置，不可早不可晚
            
            if (curRoot.left != null)
                recordPath(curRoot.left, curString, result);
            if (curRoot.right != null)
                recordPath(curRoot.right, curString, result);
        }
    }
    
    
    
    
    
}

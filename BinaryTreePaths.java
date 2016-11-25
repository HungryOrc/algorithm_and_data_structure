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
    
   
    // 另一种很巧妙的 Recursion 方法，很好地展现了整个问题的答案的构成机制：自上而下
    // Ref: https://discuss.leetcode.com/topic/23047/clean-java-solution-accepted-without-any-helper-recursive-function
    public List<String> binaryTreePaths(TreeNode root) {
        
        List<String> paths = new LinkedList<>();

        if(root == null) return paths;
        
        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }

         for (String path : binaryTreePaths(root.left)) {
             paths.add(root.val + "->" + path);
         }
         for (String path : binaryTreePaths(root.right)) {
             paths.add(root.val + "->" + path);
         }
         return paths;
    }
    
    
    // DFS
    // 2个Stack，一个装Nodes，一个装Strings！！
    // Ref: https://discuss.leetcode.com/topic/33781/my-java-solution-in-dfs-bfs-recursion
    public List<String> binaryTreePaths(TreeNode root)
    {   
        List<String> list=new ArrayList<String>();
        Stack<TreeNode> sNode=new Stack<TreeNode>();
        Stack<String> sStr=new Stack<String>();
        
        if(root==null)
           return list;
       
        sNode.push(root);
        sStr.push("");
       
        while(!sNode.isEmpty())
        {
            TreeNode curNode=sNode.pop();
            String curStr=sStr.pop();
            
            if(curNode.left==null && curNode.right==null) 
                list.add(curStr+curNode.val);
            else
            {
               if(curNode.left!=null)
               {
                   sNode.push(curNode.left);
                   sStr.push(curStr+curNode.val+"->");
               }
               if(curNode.right!=null)
               {
                   sNode.push(curNode.right);
                   sStr.push(curStr+curNode.val+"->");
               }
            }
        }
        return list;
    }
   
   
    // BFS
    // 2个Queue，一个装Nodes，一个装Strings！！
    // Ref: https://discuss.leetcode.com/topic/33781/my-java-solution-in-dfs-bfs-recursion   
    public List<String> binaryTreePaths(TreeNode root)
    {
       List<String> list = new ArrayList<String>();
       Queue<TreeNode> qNode = new LinkedList<TreeNode>();
       Queue<String> qStr = new LinkedList<String>();

       if (root==null)
          return list;
       
       qNode.add(root);
       qStr.add("");
       
       while(!qNode.isEmpty())
       {
           TreeNode curNode=qNode.remove();
           String curStr=qStr.remove();

           if (curNode.left==null && curNode.right==null) 
               list.add(curStr+curNode.val);
           else
           {
              if (curNode.left!=null) {
                  qNode.add(curNode.left);
                  qStr.add(curStr+curNode.val+"->");
              }
              if (curNode.right!=null) {
                  qNode.add(curNode.right);
                  qStr.add(curStr+curNode.val+"->");
              }
           }
       }
       return list;
   }
   
}

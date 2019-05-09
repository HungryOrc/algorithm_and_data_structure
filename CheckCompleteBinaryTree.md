/* Check if a given binary tree is completed. 
A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. 
Furthermore, all nodes are as far left as possible.
What if the binary tree is null? Return true in this case.

Examples:

        5
      /    \
    3        8
  /   \
1      4
is a complete binary tree

        5
      /    \
    3        8
  /   \        \
1      4        11
is not a complete binary tree

* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* } */

public class Solution {

  public boolean isCompleted(TreeNode root) {
    if (root == null) {
      return true;
    }
 if any node has right, but left child is null, then return false
 
 if any node has less than 2 children, then if any node after him in the queue has any child, then return false
         for example in the above graph, the first tree, after 8, there are 1 and 4 in the queue, it is fine, as long as 1 and 4 dont have any children
}

        
方法2：用 Recursion 来做
```java
class Result {
    int height;
    boolean isPerfect;
    boolean isComplete;
    public Result(int h, boolean p, boolean c) {
        height = h;
        isPerfect = p;
        isComplete = c;
    }
}

public class Solution {
    public boolean isComplete(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root).isComplete;
    }
    
    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, true, true);
        }
            
        Result left = dfs(root.left);
        Result right = dfs(root.right);
            
        int height = Math.max(left.height, right.height) + 1;
        
        boolean isPerfect = 
            (left.isPerfect) && (right.isPerfect) && (left.height == right.height);
        
        // 别忘了下面的第一个括号里的情况！很容易忽视掉！
        boolean isComplete = 
            (left.isComplete && right.isPerfect && right.height + 1 = left.height) ||
            (left.isPerfect && right.isComplete && left.height = right.height);
        
        return new Result(height, isPerfect, isComplete);
    }
}
```

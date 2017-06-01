/* Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.
Assumptions:
The given sequence is not null, and its length > 0
There are no duplicate keys in the binary search tree

Examples:
preorder traversal = {5, 3, 1, 4, 8, 11}
The corresponding binary search tree is:
        5
      /    \
    3        8
  /   \        \
1      4        11   */

/* 思路：很巧妙 ！！！
由于是 Preorder，所以给定数组（设为 int[] pre）的第一个元素即 pre[0] 一定是整个树的root的value。
然后，从第一个元素开始，从前往后看，
第一个能找到的比它小的元素，一定就是左子树的sub root；第一个能找到的比它大的元素，一定就是右子树的sub root。
而且，由于 Preorder 的数组是 先root再左再右 这样排列的，所以从前往后找的时候，一定是
先找到比root小的left sub root，再找到比root大的right sub root。这一点可以在题目里的示例数组里印证。
在这种方法下，我们也必须先把root的左子树全部处理完毕，再处理root的右子树。
5 是root，从5往后看，先看到的第一个比5小的是 3, 3就是5的左子树的sub root；第一个比5大的是8, 8就是5的右子树的sub root。

从前往后找的时候，记住下一个node的value的上限 ！！！
如果下一个node的value比这个上限要大，则证明要跳到右子树去了。如果index变为大于pre数组的长度了，证明pre数组的右尽头已经过了。
上面这两种情况，都意味着当前node的当前child要置为 null ！！！

特别注意 ！！！ 跟踪整个处理过程的变量 index，必须置为全局变量 ！！！ 
因为每个分支都要不断地修改它，并实时体现在全局 ！！！体现在后面的所有分支的处理 ！！！
如果把它作为参数传来传去，是无法实现这一点的 ！！！   */

// Time: O(n)，超牛！
public class Solution {
  
  int index; // 这个必须是全局变量 ！！！
  
  public TreeNode reconstruct(int[] pre) {
    index = 0; // 数组的head必然是整个tree的root，从这里开始，从前往后走
    return reconPostorder(pre, Integer.MAX_VALUE);
  }
  
  private TreeNode reconPostorder(int[] pre, int upperBound) {
    if (index >= pre.length || pre[index] >= upperBound) {
      return null;
    }
    
    TreeNode root = new TreeNode(pre[index]);
    index ++;
    
    root.left = reconPostorder(pre, root.key);
    root.right = reconPostorder(pre, upperBound);
    
    return root;
  }
  
}

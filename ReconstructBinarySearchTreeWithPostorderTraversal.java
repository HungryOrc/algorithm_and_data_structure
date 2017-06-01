/* Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.
Assumptions:
The given sequence is not null, and its length > 0
There are no duplicate keys in the binary search tree

Examples:
postorder traversal = {1, 4, 3, 11, 8, 5}
the corresponding binary search tree is:
        5
      /    \
    3        8
  /   \        \
1      4        11    */

/* 思路：很巧妙 ！！！
Ref: http://www.geeksforgeeks.org/construct-a-binary-search-tree-from-given-postorder/

由于是 Postorder，所以给定数组（设为 int[] post）的最后一个元素即 post[post.length - 1] 就是整个树的root的value。
然后，从最后一个元素开始，从后往前看，
第一个能找到的比它大的元素，一定就是右子树的sub root；第一个能找到的比它小的元素，一定就是左子树的sub root。
而且，由于postorder的数组是先左再右最后root这样排列的，所以从后往前找的时候，一定是
先找到比root大的right sub root，再找到比root小的left sub root。这一点可以在题目里的示例数组里印证。
在这种方法下，我们也必须先把root的右子树全部处理完毕，再处理root的左子树。
5 是root，从5往前看，先看到的第一个比5大的是 8, 8就是5的右子树的sub root；第一个比5小的是3, 3就是5的左子树的sub root。

从后往前找的时候，记住下一个node的value的下限 ！！！
如果下一个node的value比这个下限要小，则证明要跳到左子树去了。如果index变为<0了，证明post数组的左尽头已经过了。
上面这两种情况，都意味着当前node的当前child要置为 null ！！！

特别注意 ！！！ 跟踪整个处理过程的变量 index，必须置为全局变量 ！！！ 
因为每个分支都要不断地修改它，并实时体现在全局 ！！！体现在后面的所有分支的处理 ！！！
如果把它作为参数传来传去，是无法实现这一点的 ！！！   */

// Time: O(n)，超牛！
public class Solution {
  
  int index; // 这个必须是全局变量 ！！！
  
  public TreeNode reconstruct(int[] post) {
    index = post.length - 1; // 数组的尾巴必然是整个tree的root，从这里开始，从后往前走
    return reconPostorder(post, Integer.MIN_VALUE);
  }
  
  private TreeNode reconPostorder(int[] post, int lowerBound) {
    if (index < 0 || post[index] <= lowerBound) {
      return null;
    }
    
    TreeNode root = new TreeNode(post[index]);
    index --;
    
    root.right = reconPostorder(post, root.key);
    root.left = reconPostorder(post, lowerBound);
    
    return root;
  }
  
}

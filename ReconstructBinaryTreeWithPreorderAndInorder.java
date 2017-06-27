/* Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
Assumptions:
The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree

Examples:
preorder traversal = {5, 3, 1, 4, 8, 11}
inorder traversal = {1, 3, 4, 5, 8, 11}
And we can get the corresponding binary tree:
        5
      /    \
    3        8
  /   \        \
1      4        11

注：要reconstruct一个binary tree，必须有inorder。光靠preorder和postorder是不行的。因为例如下面这两棵树：
   1   以及   1
  /           \
 2             2
 它们的preorder都是{2,1}，它们的postorder都是{1,2}，光靠preorder和postorder，我们根本不可能区分原树到底是上述二者中的哪一个。
 但它们的inorder一个是{2,1}，一个是{1,2}，所以只要inorder再结合另外的任何一个order，就可以唯一地确定出原树。  */
 
 
 /* 思路：
 要点：这类题的关键就在于，用postorder或者preorder的性质，先找到root（postorder里的最后一个或者preorder里的第一个node），
 有了root以后，就可以把整个问题一分为二。每一半返回一个subtree的root node。合在一起就是整个答案   */

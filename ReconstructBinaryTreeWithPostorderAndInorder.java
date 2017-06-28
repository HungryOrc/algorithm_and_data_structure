/* Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
Assumptions:
The given sequences are not null and they have the same length
There are NO duplicate keys in the binary tree

Examples:
postorder traversal = {1, 4, 3, 11, 8, 5}
inorder traversal = {1, 3, 4, 5, 8, 11}
Then we can get the corresponding binary tree:
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
 有了root以后，就可以把整个问题一分为二。每一半返回一个subtree的root node。不断地使用recursion。最终合在一起就是整个答案 
 
 举例：比如上面题目里给的那个 binary tree，它的root是5，也是postorder的最后一位：
 postorder: 1 4 3 11 8 <5>
 inorder:   1 3 4 <5> 8 11
 然后，在inorder里面找到5，那么在inorder里，5之前的，就是5的左子树所有的nodes；5之后的，就是5的右子树所有的nodes。
 然后，就要按照这个，把postorder里的数字也都分为对应的两部分：
 postorder: 1 4 3    11 8    <5>
 inorder:   1 3 4    <5>    8 11
 然后就对inorder里的左半部分和postorder里相应的部分进行关于左子树的recursion处理；
 对inorder里的右半部分和postorder里相应的部分进行关于右子树的recursion处理。这些处理和之前对于整个树的处理是同理的。
 
 这里要特别注意！！！
 在inorder里找到当前root的index以后，这个index值可以直接用于切割inorder数组里要用于下一个recursion的左半部分和右半部分，
 但它不可以直接用来切割postorder数组里的两个部分！！！
 要切割postorder数组，必须综合使用 postorder在本次recursion里的start index，以及root在inorder里的index，这两个方面的信息 ！！！   */

/* public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * } */
 
 public class Solution {
  
  public TreeNode reconstruct(int[] in, int[] post) {
    // <value, index in the inorder array>
    HashMap<Integer, Integer> recordsInorder = new HashMap<>();
    for (int i = 0; i < in.length; i++) {
      recordsInorder.put(in[i], i);
    }
    
    return recursiveConstruct(in, 0, in.length - 1, post, 0, post.length - 1, recordsInorder);
  }
  
  private TreeNode recursiveConstruct(int[] in, int inStart, int inEnd,
    int[] post, int postStart, int postEnd, HashMap<Integer, Integer> recordsInorder) {
    
    if (inStart > inEnd || postStart > postEnd) {
      return null;
    }
    
    TreeNode curRoot = new TreeNode(post[postEnd]);
    
    int indexOfCurRootInInorder = recordsInorder.get(curRoot.key);
    int sizeOfLeftHalfInInorder = indexOfCurRootInInorder - inStart;
    
    curRoot.left = recursiveConstruct(in, inStart, indexOfCurRootInInorder - 1,
      post, postStart, postStart + sizeOfLeftHalfInInorder - 1, recordsInorder);
    
    curRoot.right = recursiveConstruct(in, indexOfCurRootInInorder + 1, inEnd,
      post, postStart + sizeOfLeftHalfInInorder, postEnd - 1, recordsInorder);
      
    return curRoot;
  }
}

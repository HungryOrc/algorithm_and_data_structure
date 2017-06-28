/* Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
Assumptions:
The given sequences are not null and they have the same length
There are NO duplicate keys in the binary tree ---> 这一条很重要！不可少！！

Examples:
levelorder traversal = {5, 3, 8, 1, 4, 11}
inorder traversal = {1, 3, 4, 5, 8, 11}
Than we can get the corresponding binary tree:
        5
      /    \
    3        8
  /   \        \
1      4        11

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

  public TreeNode reconstruct(int[] in, int[] level) {
    Map<Integer, Integer> recordsInorder = new HashMap<>();
    for (int i = 0; i < in.length; i++) {
      recordsInorder.put(in[i], i);
    }
    
    List<Integer> levelList = new ArrayList<>();
    for (int num : level) {
      levelList.add(num);
    }
    
    return recursiveConstruct(levelList, recordsInorder);
  }
  
  private TreeNode recursiveConstruct(List<Integer> level, Map<Integer, Integer> recordsInorder) {
    if (level.isEmpty()) {
      return null;
    }
    
    TreeNode curRoot = new TreeNode(level.remove(0)); // namely get the value and remove it
    
    List<Integer> leftLevel = new ArrayList<>();
    List<Integer> rightLevel = new ArrayList<>();
    
    for (int num : level) { // the 1st element in the original level list had already been removed
      int curRootValue = curRoot.key;
      if (recordsInorder.get(num) < recordsInorder.get(curRootValue)) {
        leftLevel.add(num);
      } else {
        rightLevel.add(num);
      }
    }
    
    curRoot.left = recursiveConstruct(leftLevel, recordsInorder);
    curRoot.right = recursiveConstruct(rightLevel, recordsInorder);
    
    return curRoot;
  }
}

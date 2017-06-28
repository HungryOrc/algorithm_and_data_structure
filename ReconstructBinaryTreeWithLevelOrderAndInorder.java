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
 level order的第一个元素一定是当前树的root。但后面有没有left subtree root，有没有right subtree root，有的话分别在哪里，
 就没那么好找了。不像 reconstruct with postorder + inorder，或者 reconstruct with preorder + inorder 那么清晰易见。
 
 这一题里，只能在 level order数组里，从第二个开始（第一个是root），到数组结尾为止，一个一个看它们在 in order 数组里的index！！
 （注意，本题要构建的，不是BST！只是一般的binary tree！所以元素之间不存在左小右大的关系！）
 如果本数在in order数组里的index比root在in order数组里的index小，就把本数放到一个list里去，作为将来构建当前root的左子树的元素；
 如果比root的index大，就把本数放到另一个list里去，作为将来构建当前root的右子树的元素。
 （注意，由于 level order的性质，比root大和比如root小的数，会在level order数组里交替出现！！）
 
 举例：比如上面题目里给的那个 binary tree，它的root是5，也是level order的第一位：
 levelorder: <5> 3 8 1 4 11
 inorder:    1 3 4 <5> 8 11
 然后，在inorder里面找到5，那么在inorder里，5之前的，就是5的左子树所有的nodes；5之后的，就是5的右子树所有的nodes。
 然后，就要按照这个，把level order里的数字也都分为对应的两部分：
 levelorder: <5>   3 1 4   8 11
 inorder:    1 3 4   <5>   8 11
 但是上面这一步，由于level order里比root大和小的数是交替出现的，所以不能再沿用level order数组的一部分来做，不得已只能
 重新构建2个list，一个一个往这两个list里加数！！！
 这么加的话，在每个list里，每个数的前后相对关系，是保持了与level order数组里的相对关系相一致的 ！！！
 
 然后就对inorder里的左半部分和level order里相应的部分进行关于左子树的recursion处理；
 对inorder里的右半部分和level order里相应的部分进行关于右子树的recursion处理。这些处理和之前对于整个树的处理是同理的    */

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

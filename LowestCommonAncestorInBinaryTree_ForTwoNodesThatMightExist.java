/* Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Return null if LCA does not exist. 这一题的特点是，A 与 B 都有可能并不存在
Notice: node A or node B may not exist in tree.

Example:
For the following binary tree:
  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4
LCA(5, 6) = 7
LCA(6, 7) = 7

* Definition of TreeNode:
* public class TreeNode {
*     public int val;
*     public TreeNode left, right;
*     public TreeNode(int val) {
*         this.val = val;
*         this.left = this.right = null;
*     }
* } */


// 方法1：复用 2个nodes都一定存在在 tree 里的方法，然后放两个全局变量 boolean foundOne, foundTwo。一开始都设为false。
// 在从上往下爬树的过程中，找到one，就将foundOne置为true；找到two，就将foundTwo置为true。
// 最后的结果，先按照2个nodes都一定存在的方法把所谓的答案找到，然后看上面的foundOne和foundTwo，
// 如果它们两都是true，则这个答案是ok的；如果它们两之间有一个不为true，则这个答案不符合要求，最终得返回null。
// 
// 这个方法要特别注意一个陷阱 ！！！
// 如果2个nodes都一定存在，则在某个子树上，我们一旦找到 one 或者 two，那么这个子树就不必再找下去了，可以马上直接 return 当前node了。
// 因为one和two一定都存在，而且如果另一个node就存在在当前node的下面某处的话，那么本来也应该返回当前node作为答案。
// 而这一题就不同了 ！！！
// one和two都有可能不存在。如果找到了其中的一个，必须继续向下遍历下去，因为你也不知道另一个是否在当前这个node的下面 ！！！
// 如果找到了one，然后它下面有two，则最终应返回one；如果它下面没two，则最终应该返回 null。我们找到one就直接return的话，它下面如果有two，
// 也会被埋没，然后foundTwo一直都会停留在false，那么到了最后汇总答案的时候，会认为two不存在，最终将返回null，这是不ok的
// 所以：
// 这一题里，我们先继续往下走，再判断当前node是不是one或者two ！！！！！
//
public class Solution {
  
  boolean foundOne, foundTwo;
  
  public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode one, TreeNode two) {
    if (root == null || one == null || two == null) {
      return null;
    }
    
    foundOne = false;
    foundTwo = false;
    
    TreeNode result = findLCA(root, one, two);
    if (foundOne == false || foundTwo == false) {
      return null;
    } else {
      return result;
    }
  }
  
  private TreeNode findLCA(TreeNode node, TreeNode one, TreeNode two) {
    if (node == null) {
        return null;
    } else if (node == one && node == two) { // 这是one和two本来就是同一个东西的尴尬情况...
        foundOne = true;
        foundTwo = true;
        return node;
    }
    
    TreeNode left = findLCA(root.left, one, two);
    TreeNode right = findLCA(root.right, one, two);
    // 必须先继续往下走，再判断当前node是不是one或者two ！！！！！
      
    if (root == one) {
        foundOne = true;
        return one;
    } else if (root == two) {
        foundTwo = true;
        return two;
    } else if (left != null && right != null) {
        return root;
    } else if (left != null) { // && right == null
        return left;
    } else if (right != null) { // && left == null
        return right;
    } else { // left == null && right == null
        return null;
    }
  }
}


// 方法2：九章。用自定义的 custon result class ！！
// 基于 Common Binary Tree 的 LCA 问题的基本形式，即 A 和 B 一定存在的情况。
// Ref: http://www.jiuzhang.com/solutions/lowest-common-ancestor-iii/
class ResultType {
    public boolean aExist, bExist;
    public TreeNode node;
    ResultType (boolean a, boolean b, TreeNode n) {
        aExist = a;
        bExist = b;
        node = n;
    }
}
    
public class Solution {
  
    // Divide and Conquer - Recursion
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType result = checkLCA(root, A, B);
        if (result.aExist && result.bExist) {
            return result.node;
        } else {
            return null;
        }
    }
    
    private ResultType checkLCA(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultType(false, false, null);
        }
        
        // Divide，考察左右子树的情况
        ResultType leftResult = checkLCA(root.left, A, B);
        ResultType rightResult = checkLCA(root.right, A, B);
        
        // 综合得到当前root以下(包含root本身)的整个树的情况
        boolean aExist = leftResult.aExist || rightResult.aExist || root == A;
        boolean bExist = leftResult.bExist || rightResult.bExist || root == B;
        
        if (root == A || root == B) {
            return new ResultType(aExist, bExist, root);
        }
        
        if (leftResult.node != null && rightResult.node != null) {
            return new ResultType(aExist, bExist, root);
        } else if (leftResult.node != null && rightResult.node == null) {
            return new ResultType(aExist, bExist, leftResult.node);
        } else if (leftResult.node == null && rightResult.node != null) {
            return new ResultType(aExist, bExist, rightResult.node);
        } else { // left.node == null && right.node == null
            return new ResultType(aExist, bExist, null);
        }    
    }
}

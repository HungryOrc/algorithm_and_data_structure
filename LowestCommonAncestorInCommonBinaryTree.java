/* Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Notice: Assume these 2 nodes do exist in this tree.

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

public class Solution {
    /* @param root: The root of the binary search tree.
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes. */
    
    // 非常巧妙的方法！！！
    // Ref: http://www.jiuzhang.com/solutions/lowest-common-ancestor/
    // 在root为根的二叉树中找A,B的LCA:
    // 如果找到了就返回这个LCA
    // 如果只碰到A，就返回A
    // 如果只碰到B，就返回B
    // 如果都没有，就返回null
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) {
            return null;
        }
        
        // 以下这个if语句 + 后面的conquer部分，这两者共同作用，导致了：
        // node1及其以上的各级父node，作为root被本函数作用时，会返回node1
        // node2及其以上的各级父node，作为root被本函数作用时，会返回node2
        // 但是：情况在此时会发生变化：如果一个node，其左/右子树内
        // 分别含有node1/2或node2/1，则此node被本函数作用会返回此node本身，
        // 然后此node以上的各级父node被本函数作用时还是会一直返回此node
        // 另外，如果一个node以下的子树不含node1或node2中的任一个，
        // 则此node被本函数作用时会返回null
        if (root == node1 || root == node2) {
            return root;
        }
        
        // Divide
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        
        // Conquer
        if (left != null && right != null) {
            return root;
        } else if (left != null && right == null) {
            return left;
        } else if (right != null && left == null) {
            return right;
        } else { // left == null && right == null
            return null;
        }
    }
}

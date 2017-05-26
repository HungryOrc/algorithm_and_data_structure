/* Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
The node has an extra attribute parent which point to the father of itself. The root's parent is null.

Example
For the following binary tree:
  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4
LCA(5, 6) = 7
LCA(6, 7) = 7

* Definition of ParentTreeNode:
* 
* class ParentTreeNode {
*     public ParentTreeNode parent, left, right;
* } */

// 我的方法：从 A、B 两个node 往上找
public class Solution {
    
    /* @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A,
                                                 ParentTreeNode B) {
        if (root == null || A == null || B == null) {
            return null;
        }
    
        // 注意！要从 A 开始！！！不要从 A 的 parent 开始！！！不然会漏可能性
        ParentTreeNode parentOfA = A;
        HashSet<ParentTreeNode> parentASet = new HashSet<>();
        parentASet.add(A);
        
        while (parentOfA != root) {
            parentOfA = parentOfA.parent;
            parentASet.add(parentOfA);
        }
        
        ParentTreeNode parentOfB = B;
        while (!parentASet.contains(parentOfB)) {
            parentOfB = parentOfB.parent;
        }
        return parentOfB;
    }
}

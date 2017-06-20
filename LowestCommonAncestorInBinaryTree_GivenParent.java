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
*     int val;
*     public ParentTreeNode parent, left, right;
* } */

// 我的方法：从 A、B 两个node 往上找, use a HashSet of ParentTreeNodes
public class Solution {

  public ParentTreeNode lowestCommonAncestorII(ParentTreeNode one, ParentTreeNode two) {
    if (one == null || two == null) {
      return null;
    }
    
    HashSet<ParentTreeNode> parentsOfOne = new HashSet<>();
    
    ParentTreeNode node = one;
    while (node != null) {
      parentsOfOne.add(node);
      node = node.parent;
    }
    
    node = two;
    while (node != null && !parentsOfOne.contains(node)) {
      node = node.parent;
    }
    if (node == null) {
      return null;
    } else {
      return node;
    }
  }
}

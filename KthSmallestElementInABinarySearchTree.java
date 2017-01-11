/* Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Note:  You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?
Hint:
1. Try to utilize the property of a BST.
2. What if you could modify the BST node's structure?
3. The optimal runtime complexity is O(height of BST).

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

public class Solution 
{
    public int kthSmallest(TreeNode root, int k) 
    {
        int numOfNodesInLeftSubtree = countNodes(root.left);
        
        if (numOfNodesInLeftSubtree >= k)
            return kthSmallest(root.left, k);
        else if (numOfNodesInLeftSubtree < k-1)
            // the last -1 represents the current node (root)
            return kthSmallest(root.right, k - numOfNodesInLeftSubtree - 1);
        else // numOfNodesInLeftSubtree == k-1
            return root.val;
    }
    private static int countNodes(TreeNode curNode)
    {
        if (curNode == null)
            return 0;
        return 1 + countNodes(curNode.left) + countNodes(curNode.right);
    }
}

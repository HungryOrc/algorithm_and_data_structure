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

// 以下的几个方法都很不错，受教了
// Ref: https://discuss.leetcode.com/topic/17810/3-ways-implemented-in-java-binary-search-in-order-iterative-recursive

public class Solution 
{
    // DFS Binary Search Recursion
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
    
    
    // DFS In-Order Recursion
    private static int remainingCount = 0;
    private static int value = 0;
    public int kthSmallest(TreeNode root, int k) 
    {
        remainingCount = k;
        inOrderCount(root);
        return value;
    }
    // 先数左边，再数中间，最后数右边，才能吻合从最小到最大
    private static void inOrderCount(TreeNode curNode)
    {
        if (curNode.left != null)
            inOrderCount(curNode.left);
        
        remainingCount --;
        if (remainingCount == 0)
        {
            value = curNode.val;
            return;
        }
        
        if (curNode.right != null)
            inOrderCount(curNode.right);
    }
    
    
      // DFS In-Order Iteration
      // 这个解法稍微绕一点
      public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();

        while (root != null) {
            st.push(root);
            root = root.left;
        }

        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode right = n.right;
            while (right != null) {
                st.push(right);
                right = right.left;
            }
        }

        return -1; // never hit if k is valid
    }
    
}

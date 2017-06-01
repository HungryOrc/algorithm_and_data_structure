/* Given an array of numbers, verify whether it is the correct postorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.

Example: 
{1, 3, 2} is a valid postorder traversal of BST.
{2, 3, 1} is a valid postorder traversal of BST.
{3, 2, 1} is a valid postorder traversal of BST.
{3, 3, 1} is not a valid postorder traversal of any BST.
{0, 3, 2, 1} is not a valid postorder traversal of any BST.

Follow up: Could you do it using only constant space complexity?   */


/* 我的方法，Time: O(n)
思路的缘由，参考我总结的另一题：Reconstruct BST with Postorder Traversal

一个合格的关于BST的 Preorder Traversal（这里是作为一个数组），一定是开头即整个树的 root 的value。
然后从前往后走，第一个比它小的数，一定是root的左子树的sub root的value；第一个比它大的数，一定是root的右子树的sub root的value。
然后每一个node，都必须符合一定的取值范围，即 (lowerBound, upperBound)，这些bounds是不断更新的。
只要中间过程里有一个node不吻合这些bounds，我们就停止处理数组。
如果到了最后，发现整个数组没有被处理完最后一个元素（done的标准 cur index == array.length），
则本数组必然不是一个 valid traversal of BST ！！！   */

public class Solution {
    
    int curIndex; // 必须是全局变量 ！！！
    
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length <= 1) {
            return true;
        }
        
        curIndex = postorder.length - 1;
        verifyPostorder(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        if (curIndex < 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private void verifyPostorder(int[] postorder, int lowerBound, int upperBound) {
        if (curIndex < 0 || // 数组过了头部了
        	postorder[curIndex] >= upperBound || postorder[curIndex] <= lowerBound) { // 或者有不满足bounds的node出现了
            return;
        }
        
        int curNodeValue = postorder[curIndex];
        curIndex --;
        
        // check the values of the nodes that are supposed to locate in the right subtree
        verifyPostorder(postorder, lowerBound, curNodeValue);
        // check the values of the nodes that are supposed to locate in the left subtree
        verifyPostorder(postorder, curNodeValue, upperBound);
    }   
}

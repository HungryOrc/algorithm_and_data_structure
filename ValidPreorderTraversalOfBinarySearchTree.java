/* Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.

Example: 
{1, 0, 2} is a valid preorder traversal of BST.
{2, 3, 1} is not a valid preorder traversal of any BST.

Follow up: Could you do it using only constant space complexity?   */


/* 方法1：我的方法，Time: O(n)
思路的缘由，参考我总结的另一题：Reconstruct BST with Preorder Traversal

一个合格的关于BST的 Preorder Traversal（这里是作为一个数组），一定是开头即整个树的 root 的value。
然后从前往后走，第一个比它小的数，一定是root的左子树的sub root的value；第一个比它大的数，一定是root的右子树的sub root的value。
然后每一个node，都必须符合一定的取值范围，即 (lowerBound, upperBound)，这些bounds是不断更新的。
只要有一个node不吻合这些bounds，我们就停止处理数组。
如果到了最后，发现整个数组没有被处理完最后一个元素（done的标准 cur index == array.length），
则本数组必然不是一个 valid traversal of BST ！！！   */

public class Solution {
    
    int curIndex; // 必须是全局变量 ！！！
    
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 1) {
            return true;
        }
        
        curIndex = 0;
        verifyPreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        if (curIndex == preorder.length) {
            return true;
        } else {
            return false;
        }
    }
    
    private void verifyPreorder(int[] preorder, int lowerBound, int upperBound) {
        if (curIndex == preorder.length || // 数组到尾巴了
            preorder[curIndex] >= upperBound || preorder[curIndex] <= lowerBound) { // 或者有不满足bounds的node出现了
            return;
        }
        
        int curNodeValue = preorder[curIndex];
        curIndex ++;
        
        // check the values of the nodes that are supposed to locate in the left subtree
        verifyPreorder(preorder, lowerBound, curNodeValue);
        // check the values of the nodes that are supposed to locate in the right subtree
        verifyPreorder(preorder, curNodeValue, upperBound);
    }   
}


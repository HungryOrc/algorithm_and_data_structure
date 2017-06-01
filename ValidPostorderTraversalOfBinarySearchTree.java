/* Given an array of numbers, verify whether it is the correct postorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.

Example:
Valid postorder traversals of BSTs:
{1, 3, 2}
{2, 3, 1}
{0, 3, 2, 1}
{0, 2, 3, 1}
{5, 3, 2, 1}
{2, 5, 3, 1}
Not a valid postorder traversal of any BST:
{3, 3, 1}
{5, 2, 3, 1}

Follow up: Could you do it using only constant space complexity?   */


/* 方法1：用 Stack 的 Iterative 方法
Time: O(n), Space: O(n)

Postorder，数组最右边第一个元素一定是整个树的root。先把它push进stack里。
然后从倒数第二个元素开始，从右往左看各个元素，
根据postorder的性质，从右往左看，一定是先经历右子树，再经历左子树。所以：
如果比stack顶部的元素大，那么就push到stack里去，这意味着这些新到的元素，都是处于越来越深的右子方向上。
如果比stack顶部的元素小，那么就要从stack里pop元素出来。这意味着新到的元素是stack里的元素的左子，而且
还未必是栈顶元素的左子，有可能是栈顶元素的爸爸，或者栈顶元素的爷爷的左子...... 所以要不断地pop，直到栈顶元素小于新到的元素，或者栈pop空了，
这时再把新到的元素push到栈里去。

在这个过程中，什么情况下是违反了BST的规则呢 ？？？！！！
这个过程里，我们是先从上往下地逐层填满各个右子，再从下往上地逐层填满各个左子，
如果填左子的过程里，又来了一个过大的元素，是必须填到右子去的，那就是违规了 ！！！
所以我们必须不断更新目前处理到的nodes的value的上限是多少。这个上限就在从stack里pop元素出来的时候更新 ！！！   */

public class Solution {
    
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length <= 1) {
            return true;
        }
        
        Deque<Integer> valueStack = new LinkedList<>();
        valueStack.push(postorder[postorder.length - 1]);
        
        int curUpperBound = Integer.MAX_VALUE;
        
        for (int i = postorder.length - 2; i >= 0; i--) {
            int curValue = postorder[i];
            
            if (curValue >= curUpperBound) {
                return false;
            }
            
            if (curValue > valueStack.peek()) {
                valueStack.push(curValue);
            } else {
                while (!valueStack.isEmpty() && curValue < valueStack.peek()) {
                	curUpperBound = Math.min(curUpperBound, valueStack.pop());
                }
                valueStack.push(curValue);
            }
        }
        return true;
    }
}


/* 方法2：我的 Recursion 方法，Time: O(n)
思路的缘由，参考我总结的另一题：Reconstruct BST with Postorder Traversal

一个合格的关于BST的 Postorder Traversal（这里是作为一个数组），一定是结尾即整个树的 root 的value。
然后从后往前走，第一个比它大的数，一定是root的右子树的sub root的value；第一个比它小的数，一定是root的左子树的sub root的value。
然后每一个node，都必须符合一定的取值范围，即 (lowerBound, upperBound)，这些bounds是不断更新的。
只要中间过程里有一个node不吻合这些bounds，我们就停止处理数组。
如果到了最后，发现整个数组没有被处理完最后一个元素（done的标准是 cur index == -1），
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
        verifyPostorder(postorder, curNodeValue, upperBound);
        // check the values of the nodes that are supposed to locate in the left subtree
        verifyPostorder(postorder, lowerBound, curNodeValue);
    }   
}

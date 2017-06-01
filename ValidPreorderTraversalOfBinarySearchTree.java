/* Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.

Example: 
{1, 0, 2} is a valid preorder traversal of BST.
{2, 3, 1} is not a valid preorder traversal of any BST.

Follow up: Could you do it using only constant space complexity?   */


/* 方法1：用 Stack 的 Iterative 方法
Time: O(n), Space: O(n)
Ref: https://discuss.leetcode.com/topic/21217/java-o-n-and-o-1-extra-space

Preorder，数组左边第一个元素一定是整个树的root。先把它push进stack里。
然后从第二个元素开始，从左往右看各个元素，
如果比stack顶部的元素小，那么就push到stack里去，这意味着这些新到的元素，都是处于越来越深的左子方向上。
如果比stack顶部的元素大，那么就要从stack里pop元素出来。这意味着新到的元素是stack里的元素的右子，而且
还未必是栈顶元素的右子，有可能是栈顶元素的爸爸，或者栈顶元素的爷爷的右子...... 所以要不断地pop，直到栈顶元素大于新到的元素，或者栈pop空了，
这时再把新到的元素push到栈里去。

在这个过程中，什么情况下是违反了BST的规则呢 ？？？！！！
这个过程里，我们是先从上往下地逐层填满各个左子，再从下往上地逐层填满各个右子，
如果填右子的过程里，又来了一个过于小的元素，是必须填到左子去的，那就是违规了 ！！！
所以我们必须不断更新目前处理到的nodes的value的下限是多少。这个下限就在从stack里pop元素出来的时候更新 ！！！   */

public class Solution {
    
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 1) {
            return true;
        }
        
        Deque<Integer> valueStack = new LinkedList<>();
        valueStack.push(preorder[0]);
        
        int curLowerBound = Integer.MIN_VALUE;
        
        for (int i = 1; i < preorder.length; i++) {
            int curValue = preorder[i];
            
            if (curValue <= curLowerBound) {
                return false;
            }
            
            if (curValue < valueStack.peek()) {
                valueStack.push(curValue);
            } else {
                while (!valueStack.isEmpty() && curValue > valueStack.peek()) {
                    curLowerBound = Math.max(curLowerBound, valueStack.pop());
                }
                valueStack.push(curValue);
            }
        }
        return true;
    }
}

// 上面的方法的优化：空间从 O(n) -> O(1)
// Ref: https://discuss.leetcode.com/topic/21217/java-o-n-and-o-1-extra-space
// Use the give array as the stack:
public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE, i = -1;
    for (int p : preorder) {
        if (p < low)
            return false;
        while (i >= 0 && p > preorder[i])
            low = preorder[i--];
        preorder[++i] = p;
    }
    return true;
}


/* 方法2：我的 Recursion 方法，Time: O(n)
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

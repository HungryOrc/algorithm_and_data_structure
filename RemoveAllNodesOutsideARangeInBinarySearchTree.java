/* 比如，下面这个树，remove 所有小于4 以及 大于10 的nodes之后，得到右边那个树：

        8                  8
     /    \               / \
    3     11      =>     5   9
   / \    / \             \
 -1  5   9  13             7
      \
       7
       
特别注意一个node被删除以后，它下面的children往上接入它parent的情况 ！！！
上面的例子中，
3 因为小于4而被删除，3的左边的nodes一定会更小，也必然被删除，只有3的右边的nodes有可能被接到3的parent的下面，继承3曾经的左子身份
11因为大于10而被删除，11右边的nodes一定会更大，也必然被删除，只有11左边的nodes有可能被接到11的parent的下面，继承11曾经的右子身份  */

// 代码虽然短，但思路非常精妙 ！！！ 要好好理解 ！！！

public class Solution {
        
    // Assumption: lb (lower bound) < ub (upper bound)
    public TreeNode removeNodesOutsideRange(TreeNode root, int lb, int ub) {
        if (root == null) {
            return null;
        }
        
        root.left = removeNodesOutsideRange(root.left, lb, ub);
        root.right = removeNodesOutsideRange(root.right, lb, ub);
        
        if (root.key < lb) {
            return root.right;
        }
        else if (root.key > ub) {
            return root.left;
        }
        else {
            return root;
        }
    }
}

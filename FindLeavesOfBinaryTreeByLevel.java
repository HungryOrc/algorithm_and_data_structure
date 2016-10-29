/* Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
Example: Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1]

Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}  */

// 巧妙方法：本质上其实只是把所有的 node 按照各自的 height（即距离root有几层）进行分类
// Reference: https://discuss.leetcode.com/topic/49194/10-lines-simple-java-solution-using-recursion-with-explanation
/* The ESSENTIAL of this problem is not to find the leaves, but group leaves of same level together and also to cut the tree. 
The helper function returns the level which is the distance from its furthest subtree leaf to root, 
which helps to identify which group the root belongs to.   */

public class Solution {
    
    public List<List<Integer>> findLeaves(TreeNode root)
    {
        List<List<Integer>> output = new ArrayList<>();
        heightOfThisNode(root, output);
        return output;
    }
    
    private int heightOfThisNode(TreeNode node, List<List<Integer>> output)
    {
        if (node == null)
            return -1;
        
        // 因为上面设计的，输入的参数node为null时返回值为-1，
        // 所以如果整棵树只有一个root，没有任何别的node，则root的height为 -1 + 1 = 0
        int level = Math.max(
            heightOfThisNode(node.left, output), heightOfThisNode(node.right, output)) + 1;
        
        // 如上所述，因为 root 的 height 为 0，即level值从0开始，
        // 所以最终结果要包含 level+1 个 List
        if(output.size() < level+1)
            output.add(new ArrayList<>());
        
        output.get(level).add(node.val);
        
        return level;
    }

}

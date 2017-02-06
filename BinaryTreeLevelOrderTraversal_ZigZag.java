/* Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

Example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * } */
 
public class Solution {
    /* @param root: The root of binary tree.
     * @return: A list of lists of integer include 
     *          the zigzag level order traversal of its nodes' values */
    
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> curLevelStack = new Stack<>();
        Stack<TreeNode> nextLevelStack = new Stack<>();
        curLevelStack.push(root);
        boolean leftToRight = true;
        
        while (!curLevelStack.isEmpty()) {
            
            int curLevelSize = curLevelStack.size();
            ArrayList<Integer> curLevelVals = new ArrayList<>();
            
            if (leftToRight) {
                
                for (int i = 0; i < curLevelSize; i++) {
                    TreeNode curNode = curLevelStack.pop();
                    curLevelVals.add(curNode.val);
                    
                    // 先放入左子node，再放入右子node，
                    // 这样在处理下一层时，就会先pop出右，再pop出左
                    if (curNode.left != null) {
                        nextLevelStack.push(curNode.left);
                    }
                    if (curNode.right != null) {
                        nextLevelStack.push(curNode.right);
                    }
                }
            } 
            else { // !leftToRight
                
                for (int i = 0; i < curLevelSize; i++) {
                    TreeNode curNode = curLevelStack.pop();
                    curLevelVals.add(curNode.val);
                    
                    // 先放入右子node，再放入左子node，
                    // 这样在处理下一层时，就会先pop出左，再pop出右
                    if (curNode.right != null) {
                        nextLevelStack.push(curNode.right);
                    }
                    if (curNode.left != null) {
                        nextLevelStack.push(curNode.left);
                    }
                }
            }
            result.add(curLevelVals);
            
            leftToRight = !leftToRight;
            
            curLevelStack = nextLevelStack;
            nextLevelStack = new Stack<>();
        }
        return result;
    }
}

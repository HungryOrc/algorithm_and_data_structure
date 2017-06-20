/* Get the list of keys in a given binary tree layer by layer in zig-zag order.

Examples:
        5
      /    \
    3        8
  /   \        \
 1     4        11
the result is [5, 3, 8, 11, 4, 1].

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * } */
 

// 方法1：我的方法。很给力，哈哈。用2个Stack：curLayer和nextLayer，再加上一个左到右/右到左的 boolean flag
public class Solution {
    
    public List<Integer> zigzagLevelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> curLevelStack = new Stack<>();
        Stack<TreeNode> nextLevelStack = new Stack<>();
        curLevelStack.push(root);
        boolean rightToLeft = false;
        
        while (!curLevelStack.isEmpty()) {
            int curLevelSize = curLevelStack.size();
            
            if (rightToLeft) {                
                for (int i = 0; i < curLevelSize; i++) {
                    TreeNode curNode = curLevelStack.pop();
                    result.add(curNode.key);
                    
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
            else { // !rightToLeft                
                for (int i = 0; i < curLevelSize; i++) {
                    TreeNode curNode = curLevelStack.pop();
                    result.add(curNode.key);
                    
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
            
            rightToLeft = !rightToLeft; // 切换flag
            
            curLevelStack = nextLevelStack; // 将 cur layer 指向 next layer
            nextLevelStack = new Stack<>(); // 将 next layer 置为新的空的stack
        }
        return result;
    }
}


// 方法2：用 Deque 做，两头进出。另外也需要一个左右方向的boolean flag。代码看起来简洁不少，原理上其实和上面的方法是一样的
public class Solution {
  
  public List<Integer> zigZag(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        deque.offerFirst(root);
        
        boolean rightToLeft = true;
        
        while (!deque.isEmpty()) {
            int curLevelSize = deque.size();
            
            if (!rightToLeft) { // left to right                
                for (int i = 0; i < curLevelSize; i++) {
                    TreeNode curNode = deque.pollLast();
                    result.add(curNode.key);
                    
                    // 先放入左子node，再放入右子node，
                    // 这样在处理下一层，从右往左时，就会先pop出右，再pop出左
                    if (curNode.left != null) {
                        deque.offerFirst(curNode.left);
                    }
                    if (curNode.right != null) {
                        deque.offerFirst(curNode.right);
                    }
                }
            } 
            else { // right to left          
                for (int i = 0; i < curLevelSize; i++) {
                    TreeNode curNode = deque.pollFirst();
                    result.add(curNode.key);
                    
                    // 先放入右子node，再放入左子node，
                    // 这样在处理下一层， 从左往右时，就会先pop出左，再pop出右
                    if (curNode.right != null) {
                        deque.offerLast(curNode.right);
                    }
                    if (curNode.left != null) {
                        deque.offerLast(curNode.left);
                    }
                }
            }
            rightToLeft = !rightToLeft; // 切换flag
        }
        return result;
    }
}

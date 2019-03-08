 

    
    
    // 方法2: Iteration, by 2 Stacks, DFS
    // 如果用Queue做BFS，也是大同小异
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(0);
        
        while(!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            int curSum = sumStack.pop();
            curSum += curNode.val;
            
            if (curSum == sum && curNode.left == null && curNode.right == null)
                return true;
            
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                sumStack.push(curSum);
            }
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                sumStack.push(curSum);
            }
        }
        return false;
    }
    
}

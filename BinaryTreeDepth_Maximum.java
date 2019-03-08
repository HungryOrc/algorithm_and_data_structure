
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        
        int curDepth = 0;
        
        while (!nodeQueue.isEmpty())
        {
            int numOfRemainingNodesInCurDepth = nodeQueue.size();
            
            while (numOfRemainingNodesInCurDepth > 0)
            {
                TreeNode curNode = nodeQueue.poll();
                
                if (curNode.left != null)
                    nodeQueue.offer(curNode.left);
                if (curNode.right != null)
                    nodeQueue.offer(curNode.right);
                    
                numOfRemainingNodesInCurDepth --;
            }
            
            curDepth ++;
        }
        
        return curDepth;
    }
 
    public int maxDepth_ByDFS(TreeNode root)
    {
        
    }

}

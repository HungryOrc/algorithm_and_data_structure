/* Find the number connected component in the undirected graph. 
Each node in the graph contains a label and a list of its neighbors. 
(a connected component (or just component) of an undirected graph is a subgraph in which any two vertices 
are connected to each other by paths, and which is connected to no additional vertices in the supergraph.)

Notice
Each connected component should sort by label.

Example
Given graph:
A------B  C
 \     |  | 
  \    |  |
   \   |  |
    \  |  |
      D   E
Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}

/* Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x; 
 *         neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * }; */

public class Solution {
    
    /* @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
            return result;
        }
        
        HashSet<UndirectedGraphNode> visitedNodes = new HashSet<>();
        for (UndirectedGraphNode curNode : nodes) {
            if (!visitedNodes.contains(curNode)) {
                getOneWholeComponent(curNode, visitedNodes, result);
            }
        }
        return result;
    }
    private void getOneWholeComponent(UndirectedGraphNode curNode, 
        HashSet<UndirectedGraphNode> visitedNodes,
        List<List<Integer>> result) {
            
        Queue<UndirectedGraphNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(curNode);
        ArrayList<Integer> curComponent = new ArrayList<>();
        
        while (!nodeQueue.isEmpty()) {
            UndirectedGraphNode node = nodeQueue.poll();
            
            // 注意！！这里要再检查一次HashSet里是否已经含有此node！！
            // 因为在上一轮的加neighbors进Queue的过程中，有可能
            // 有两个node都有neighbor是node x，node x 当时并没有进入Queue，
            // 所以会被加入Queue两次！！！
            if (!visitedNodes.contains(node)) {
                visitedNodes.add(node);
                curComponent.add(node.label);
            }
            
            for (UndirectedGraphNode neighbor : node.neighbors) {
                if (!visitedNodes.contains(neighbor)) {
                    nodeQueue.offer(neighbor);
                }
            }
        }
        
        // 题目要求每个list里面的元素都要从小到大排
        Collections.sort(curComponent);
        result.add(curComponent);
    } 
    
}

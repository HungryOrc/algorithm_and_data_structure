/* Given a undirected graph, a node and a target, return the nearest node to given node which value of it is target, 
return NULL if you can't find.
There is a mapping store the nodes' values in the given parameters.
It's guaranteed there is no more than one available solution.

Example
2------3  5
 \     |  | 
  \    |  |
   \   |  |
    \  |  |
      1 --4
Give a node 1, target is 50

there a hash named values which is [3,4,10,50,50], represent:
Value of node 1 is 3
Value of node 2 is 4
Value of node 3 is 10
Value of node 4 is 50
Value of node 5 is 50

Return node 4.

* Definition for graph node.
* class UndirectedGraphNode {
*     int label;
*     ArrayList<UndirectedGraphNode> neighbors;
*     UndirectedGraphNode(int x) { 
*         label = x; neighbors = new ArrayList<UndirectedGraphNode>(); 
*     }
* }; */

public class Solution {
    /* @param graph a list of Undirected graph node
     * @param values a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node an Undirected graph node
     * @param target an integer
     * @return a node */
    
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
        if (node == null) {
            return null;
        }
        
        Queue<UndirectedGraphNode> manageNodes = new LinkedList<>();
        HashSet<UndirectedGraphNode> visitedNodes = new HashSet<>();
        manageNodes.offer(node);
        visitedNodes.add(node);
        
        while (!manageNodes.isEmpty()) {
            int levelSize = manageNodes.size();
            
            for (int i = 0; i < levelSize; i++) {
                UndirectedGraphNode curNode = manageNodes.poll();
                if (values.get(curNode) == target) {
                    return curNode;
                }
                
                for (UndirectedGraphNode neighbor : curNode.neighbors) {
                    if (!visitedNodes.contains(neighbor)) {
                        visitedNodes.add(neighbor);
                        manageNodes.offer(neighbor);
                    }
                }
            }
        }
        return null;
    }
}

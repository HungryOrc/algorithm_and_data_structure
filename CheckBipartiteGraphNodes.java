/* Determine if an undirected graph is bipartite. 
A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes 
in the same group.
Assumptions: The graph is represented by a list of nodes, and the list of nodes is neither null nor empty.

Examples:

1  --   2
    /   
3  --   4
is bipartite (1, 3 in group1; 2, 4 in group2)

1  --   2
    /   |
3  --   4
is not bipartite

1       2
|       |
3       4
is bipartite (1, 2 in group1; 3, 4 in group2)

1       2
|       
3
is bipartite (1, 2 in group1; 3 in group2) 


* public class GraphNode {
*   public int key;
*   public List<GraphNode> neighbors;
*   public GraphNode(int key) {
*     this.key = key;
*     this.neighbors = new ArrayList<GraphNode>();
*   }
* } */

public class Solution {
  
  public boolean isBipartite(List<GraphNode> graph) {
    if (graph == null || graph.size() == 0) {
      return true;
    }
    
    // <node object, group label>
    // group label: 0 or 1
    HashMap<GraphNode, Integer> nodesVisitedAndGroup = new HashMap<>();
    
    for (GraphNode node : graph) {
      if (!nodesVisitedAndGroup.containsKey(node)) {
        
        if (checkBipartite(node, nodesVisitedAndGroup) == false) {
          return false;
        }
      }
    }
    
    return true;
  }
  
  private boolean checkBipartite(GraphNode node, HashMap<GraphNode, Integer> nodesVisitedAndGroup) {
    // zhi yao shi contain de, yi ding shi jing shou guo kao yan de
    if (nodesVisitedAndGroup.containsKey(node)) {
      return true;
    }
    
    // 
    nodesVisitedAndGroup.put(node, 0);
    
    Queue<GraphNode> connectedNodes = new LinkedList<>();
    connectedNodes.offer(node);
    
    while (!connectedNodes.isEmpty()) {
      GraphNode curNode = connectedNodes.poll();
      
      int curGroup = nodesVisitedAndGroup.get(curNode);
      
      // cur group = 0 => neighbors group = 1
      // cur group = 1 => neighbors group = 0
      int neighborsGroup = curGroup == 1 ? 0 : 1;
      
      for (GraphNode nei : curNode.neighbors) {
        
        if (nodesVisitedAndGroup.containsKey(nei)) {
          if (nodesVisitedAndGroup.get(nei) != neighborsGroup) {
            return false;
          }
        }
        
        // the map does not contain this neighbor node,
        // namely this neighbor node has not been visited yet
        else { 
          nodesVisitedAndGroup.put(nei, neighborsGroup);
          
          connectedNodes.offer(nei);
        }
      }
    }
    
    return true;
  }
  
}

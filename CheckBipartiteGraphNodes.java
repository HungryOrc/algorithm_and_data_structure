/* Determine if an undirected graph is bipartite. 
A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes 
in the same group.
Assumptions: The graph is represented by a list of nodes, and the list of nodes is neither null nor empty.

Examples:

1 --  2
   /   
3 --  4
is bipartite (1, 3 in group1; 2, 4 in group2)

1 --  2
   /  |
3 --  4
is not bipartite

1     2
|     |
3     4
is bipartite (1, 2 in group1; 3, 4 in group2)

1     2
|       
3
is bipartite (1, 2 in group1; 3 in group2) 

1     2                  is bipartite
1     2     3            is bipartite
1     2     3     4      is bipartite
... any number of completely unconnected nodes can form a bipartite pattern,
because we can group them randomly into 2 groups, and there will surely be no any connection among any nodes in each group

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
    
    // 关键 ！！！
    // 用一个 HashMap 同时完成 visited 的检查，和 记录每个node的所属组别 这2个任务 ！！！
    // <node object, group label> 
    // group label: 0 or 1
    HashMap<GraphNode, Integer> nodesVisitedAndGroup = new HashMap<>();
    
    for (GraphNode node : graph) {
      
      if (!nodesVisitedAndGroup.containsKey(node)) {
        // 如果本 graph 里的所有nodes都是联通在一起的，则 check 一个node的bipartite与否 之后，
        // 所有node的bipartite与否 都会被检测到（通过邻居关系不断BFS出去）
        if (checkBipartite(node, nodesVisitedAndGroup) == false) {
          return false;
        } // 只要graph里有一个node不是bipartite，那么整个graph就不是 bipartite
      }
    }
    
    return true; // 如果graph里的所有nodes都是bipartite，则返回true
  }
  
  // BFS
  private boolean checkBipartite(GraphNode node, HashMap<GraphNode, Integer> nodesVisitedAndGroup) {
    // 只要是contain在map里的node，就一定是经受过了考验的node ！！
    if (nodesVisitedAndGroup.containsKey(node)) {
      return true;
    }
    
    // 这个node一定是当前的 connected nodes cluster 的第一个被测试的node ！！
    // 所以认为它是 group0 或 group1 都可以 ！！！
    nodesVisitedAndGroup.put(node, 0);
    
    Queue<GraphNode> connectedNodes = new LinkedList<>(); // 用于 BFS
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
        
        // this is when the map does not contain this neighbor node,
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

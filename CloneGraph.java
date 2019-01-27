
public class Solution {
  
  public List<GraphNode> copy(List<GraphNode> graph) {
    List<GraphNode> graphCopy = new ArrayList<>();
    HashMap<GraphNode, GraphNode> map = new HashMap<>();
    
    for (int i = 0; i < graph.size(); i++)
      copy(graph.get(i), graphCopy, map);
    return graphCopy;
  }
  
  private GraphNode copy(GraphNode node, List<GraphNode> graphCopy,
    HashMap<GraphNode, GraphNode> map) {
    
    GraphNode nodeCopy = map.get(node);
    if (nodeCopy != null) {
      return nodeCopy;
    }
    
    nodeCopy = new GraphNode(node.key);
    map.put(node, nodeCopy);
    graphCopy.add(nodeCopy);
    
    for (GraphNode nei : node.neighbors) {
      nodeCopy.neighbors.add(copy(nei, graphCopy, map));
    }
    
    return nodeCopy;
  }
}

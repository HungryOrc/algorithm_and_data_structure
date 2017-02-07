/* Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.
Notice:
You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example:
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false. */

// Ref: http://www.jiuzhang.com/solutions/graph-valid-tree/
public class Solution {
    /* @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false */
    
    // 验证一个图是不是一个树，有以下两点充要条件：
    // 首先，边数必须等于节点数 - 1
    // 第二，所有的节点必须都是连通的
    public boolean validTree(int n, int[][] edges) {
        
        if (n == 0) {
            return false;
        }
        if (edges.length != n - 1) {
            return false;
        }
        
        HashMap<Integer, HashSet<Integer>> graphStructure = getGraphStructure(n, edges);
        
        HashSet<Integer> visitedNodes = new HashSet<>();
        visitedNodes.add(0);
        int numOfVisitedNodes = 1;
        
        Queue<Integer> managedNodes = new LinkedList<>();
        managedNodes.offer(0);
        while (!managedNodes.isEmpty()) {
            Integer curNode = managedNodes.poll();
            
            for (Integer neighbor : graphStructure.get(curNode)) {
                if (!visitedNodes.contains(neighbor)) {
                    managedNodes.offer(neighbor);
                    
                    visitedNodes.add(neighbor);
                    numOfVisitedNodes ++;
                }
            }
        }
        return (numOfVisitedNodes == n);
    }
    private HashMap<Integer, HashSet<Integer>> getGraphStructure(int n, int[][] edges) {
        
        HashMap<Integer, HashSet<Integer>> graphStructure = new HashMap<>();
        // set an empty HashSet for each node (from node 0 to node n-1) to store their neighbors respectively
        for (int i = 0; i < n; i++) {
            graphStructure.put(i, new HashSet<Integer>());
        }
        
        for (int[] edge : edges) {
            int startNode = edge[0];
            int endNode = edge[1];
            
            // add the relationship of neighbors, for both sides
            graphStructure.get(startNode).add(endNode);
            graphStructure.get(endNode).add(startNode);
        }
        return graphStructure;
    }
    
}

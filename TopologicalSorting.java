/* Given an directed graph, a topological order of the graph nodes is defined as follow:
For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.
Notice: You can assume that there is at least one topological order in the graph.

Example
For graph as follow: <此处有配图>
本题链接：http://www.lintcode.com/en/problem/topological-sorting/#
The topological order can be:
[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...

Definition for Directed graph:
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
}; 
每一条有向边，即每一个node的neighbors关系，
本node就是有向边的起始点，每一个neighbor都各是一个终止点。
如果点A有一个neighbor为点B，则点B并没有neighbor是点A，除非它们构成循环。
上例中，B的 in-degree 就多了1，而A的 in-degree 没受影响。 */

public class Solution {
    /* @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph. */   
     
    /* 九章答案：http://www.jiuzhang.com/solutions/topological-sorting/
    1 计算所有点的 in-degree
    2 把所有 in-degree == 0 的点都放到queue里去
    3 把所有的点的 in-degree 都 -1，包括in-degree已经为零甚至为负数的点！！因为成为0只能最多有一次！！
    4 BFS上述过程 */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> topoOrder = new ArrayList<>();
        if (graph == null || graph.size() == 0) {
            return topoOrder;
        }
        
        // 存储每一个node的 in-degree 值
        HashMap<DirectedGraphNode, Integer> inDegrees = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            // 每一个node的初始in-degree都是零
            inDegrees.put(node, 0);
        }
        for (DirectedGraphNode fromNode : graph) {
            for (DirectedGraphNode toNode : fromNode.neighbors) {
                // 对每一个node来说，每多一条边指向它，则它的in-degree就多1
                inDegrees.put(toNode, inDegrees.get(toNode) + 1);
            }
        }
        
        // 用BFS - Queue 来处理所有nodes的拓扑排序
        Queue<DirectedGraphNode> nodeQueue = new LinkedList<>();
        // 先把天生in-degree就是零的点(们)放到queue里，
        // 同时也把它们放到result里，它们就是排在最前面的那些nodes
        for (DirectedGraphNode node : graph) {
            if (inDegrees.get(node) == 0) {
                nodeQueue.offer(node);
                topoOrder.add(node);
            }
        }
        // 调查后面那些nodes的拓扑顺序
        while (!nodeQueue.isEmpty()) {
            DirectedGraphNode curNode = nodeQueue.poll();
            // 每个node，看它所指向的所有 neighbor nodes
            for (DirectedGraphNode neighbor : curNode.neighbors) {
                // 每个neighbor，上来二话不说，先 -1 degree 再说
                inDegrees.put(neighbor, inDegrees.get(neighbor) - 1);
                /* 如果-1之后成了零，那么它们就应被放入result，
                且它们也成为了下一批queue里应优先被处理的nodes，所以也要放入queue里。
                这里要注意了！！！
                一个天生就入度为零的节点，它所指向的一个入度为1的节点，
                就应该是下一批的最优先的节点，这些节点也是第一批被转化为入度零的节点。这里就要注意两个问题：
                1. 都是入度为1的节点，比如A和B，
                   它们的入度是来自那些天生入度为零的节点，还是来自那些天生入度大于零的节点，决定了A和B的地位高下有别
                2. 一个天生入度为零的节点，比如C，所直接连接的节点中，有的可能入度为1，有的可能入度很高，后者的地位依然是较低的，
                   不因它与C相连而受到决定性的高地位。当然它在我们的处理过程中会被优先地 -1 入度。在此之后，就看其他因素了 */
                if (inDegrees.get(neighbor) == 0) {
                    nodeQueue.offer(neighbor);
                    topoOrder.add(neighbor);
                }
            }
        }
        return topoOrder;
    }
    
}

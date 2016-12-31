/* Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Example 3:
     0 --- 1      1 --- 2
Given n = 4 and edges = [[0, 1], [1, 2]], return 2. Since there must be a lonely 3 that exists as a single vertex.

Example 4:
Given n = 4 and edges = [], return 4. We regard this situation as 4 independent vertice.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] 
and thus will not appear together in edges. */

public class Solution 
{




    // 我的朴素方法。很慢
    // 逐个检视每个edge，看它的两个端点在哪些components即分块里出现过。把包含同一个edge的任何一个端点的所有components都记录下来
    // 然后把它们合并成一个component，这里用一个HashSet来表示一个comoponent，再用一个ArrayList来存储所有的HashSets
    // 合并以后，再把被合并掉的components即HashSets从ArrayList里删掉
    // 最后看ArrayList里还剩下几个HashSet，以及还有多少个1-n之间的整数没有被任何edge即任何HashSet包含过，它们
    // 就是单独的独立的点，它们中的每一个也要算作一个component
    public int countComponents(int n, int[][] edges) 
    {
        if (edges == null || edges.length==0)
            return n;
        
        ArrayList<HashSet<Integer>> allComponents = new ArrayList<>();
        int[] firstEdge = edges[0];
        HashSet<Integer> firstComponent = new HashSet<>();
        firstComponent.add(firstEdge[0]);
        firstComponent.add(firstEdge[1]);
        allComponents.add(firstComponent);
        
        for (int i = 1; i < edges.length; i++)
        {
            int[] curEdge = edges[i];
            HashSet<Integer> containingComponents = new HashSet<>();
            
            for (int j = 0; j < 2; j ++)
            {
                int curInt = curEdge[j];
                for (int k = 0; k < allComponents.size(); k++)
                {
                    if (allComponents.get(k).contains(curInt))
                        containingComponents.add(k);
                }
            }
            
            if (containingComponents.size() > 0)
            {
                ArrayList<Integer> containingComponents_AL = new ArrayList(containingComponents);
                HashSet<Integer> combineToSet = allComponents.get(containingComponents_AL.get(0));
                combineToSet.add(curEdge[0]);
                combineToSet.add(curEdge[1]);
                for (int p = 1 ; p < containingComponents_AL.size(); p++)
                    combineToSet.addAll(allComponents.get(containingComponents_AL.get(p)));
                int shift = 0;
                for (int p = 1 ; p < containingComponents_AL.size(); p++)
                {
                    allComponents.remove(containingComponents_AL.get(p) - shift);
                    shift++;
                }
            }
            else
            {
                HashSet<Integer> newComponent = new HashSet<>();
                newComponent.add(curEdge[0]);
                newComponent.add(curEdge[1]);
                allComponents.add(newComponent);
            }
        }
        
        // 如果一共有4个数，但所有已经出现的edge里只包含1,2,3，
        // 则认为4作为一个单独的节点未被列出在任何edge里
        int numOfOccurredNumbers = 0;
        for (int i = 0; i < allComponents.size(); i++)
            numOfOccurredNumbers += allComponents.get(i).size();
            
        return allComponents.size() + (n - numOfOccurredNumbers);
    }
}

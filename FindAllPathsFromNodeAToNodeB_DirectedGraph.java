/* Given a Directed Graph (represented by each node and the nodes it can go to), 
find all the paths starting from a node to another node. Such as: from node A to node J,
the data will be stored in a txt file as follows:

A J
A : B C
B : C E G
C : A F
D : C J
F : B H
G : C D
H : A B F I
I : B

注意：
有向边，所以A能到B，不代表着B能到A
不是所有点都有边从它出发！！！这个很容易忽略！！！ */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class PathFinder {

    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "E:\\My_Java\\AllPathsInGraph\\bin\\input.txt";
        if (args.length > 0) {
        	filename = args[0];
        }
        
        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }
    
    static List<String> parseFile(String filename)
    		throws FileNotFoundException, IOException {

        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
        	allLines.add(line);
        }
        input.close();

        return parseLines(allLines);    	
    }
    
    static List<String> parseLines(List<String> lines) {
    	
		List<String> result = new ArrayList<String>();
		
		if (lines.size() <= 1 || lines.get(0).length() != 3) {
			return result;
		}
		
		String startAndEnd = lines.get(0);
		String startNode = startAndEnd.substring(0, 1);
		String endNode = startAndEnd.substring(2,3);
		
		HashMap<String, HashSet<String>> directedEdges = new HashMap<>();
		for (int i = 1; i < lines.size(); i++) {
			String[] curEdges = lines.get(i).split(" ");

			String edgeStartNode = curEdges[0];
			
			HashSet<String> edges = new HashSet<>();
			for (int j = 2; j < curEdges.length; j++) {
				edges.add(curEdges[j]);	
			}
			directedEdges.put(edgeStartNode, edges);
		}
		
		// dfs
		HashSet<String> visitedNodes = new HashSet<>();
		visitedNodes.add(startNode);
		String path = startNode;
		findAllPaths(startNode, endNode, path, visitedNodes, directedEdges, result);

    	return result;
    }
	
	static void findAllPaths(String curNode, String endNode, 
							 String path,
							 HashSet<String> visitedNodes,
							 HashMap<String, HashSet<String>> directedEdges,
							 List<String> result) {
		
		if (curNode.equals(endNode)) {
			result.add(new String(path));
			return;
		}
		
		HashSet<String> edges = directedEdges.get(curNode);
		// 注意！！！有这样的可能性：有的node根本就没有从它出发的edge！！！
		if (edges != null) {
			for (String node : edges) {
				if (!visitedNodes.contains(node)) {
					
					String newPath = path + node;					
					visitedNodes.add(node);
					
					findAllPaths(node, endNode, newPath, visitedNodes, directedEdges, result);
	
					visitedNodes.remove(node);
				}
			}
		}
	}
	
}

/* Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
All words have the same length.
All words contain only lowercase alphabetic characters.

Example
Given: start = "hit", end = "cog", dict = ["hot","dot","dog","lot","log"]
Return:
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ] */


// 方法1：我的方法。BFS - Iteration with 2 Queues
// 用BFS找所有路径，如果到了某一层，出现了有效的path，则不再深入到下一层去了。速度较慢。
public class Solution {

    HashMap<String, HashSet<String>> neighborsOfStringsInTheDict;

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {

        List<List<String>> shortestPaths = new ArrayList<>();
        if (start == null || end == null || start.length() != end.length()) {
            return shortestPaths;
        }
        
        neighborsOfStringsInTheDict = new HashMap<>();
        
        dict.add(start);
        dict.add(end);
        
        Queue<String> managedStrings = new LinkedList<>();
        managedStrings.offer(start);
        
        Queue<ArrayList<String>> managedPaths = new LinkedList<>();
        ArrayList<String> path = new ArrayList<>();
        path.add(start);
        managedPaths.offer(path);
        
        // shortestPaths.size() == 0 表示我们还没有得到任何有效的path
        while(!managedStrings.isEmpty() && shortestPaths.size() == 0) {
            int sizeOfCurLayer = managedStrings.size();
            for (int i = 0; i < sizeOfCurLayer; i++) {
                
                String curString = managedStrings.poll();
                ArrayList<String> curPath = managedPaths.poll();
                
                if (curString.equals(end)) {
                    shortestPaths.add(curPath);
                    continue;
                }
                
                if (shortestPaths.size() == 0) {
                    HashSet<String> curNeighbors = new HashSet<>();
                    if (!neighborsOfStringsInTheDict.containsKey(curString)) {
                        curNeighbors = findAllNeighbors(curString, dict);
                        neighborsOfStringsInTheDict.put(curString, curNeighbors);
                    }
                    curNeighbors = neighborsOfStringsInTheDict.get(curString);
                    
                    for (String neighbor : curNeighbors) {
                        
                        if (!curPath.contains(neighbor)) {
                            ArrayList<String> nextPath = new ArrayList<>(curPath);
                            nextPath.add(neighbor);
                            managedPaths.offer(nextPath);
                            
                            managedStrings.offer(neighbor);
                        }
                    }
                }
            }
        }
        return shortestPaths;
    }

    private HashSet<String> findAllNeighbors(String string, Set<String> dict) {

        HashSet<String> neighbors = new HashSet<>();

        for (int i = 0; i < string.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {

                char[] cArray = string.toCharArray();
                if (cArray[i] != c) {
                    cArray[i] = c;
                    String neighbor = new String(cArray);

                    if (dict.contains(neighbor)) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }
}


// 方法2：我的方法。BFS - Recursion
// 用BFS找所有路径，不断更新最短路径，比最短路径长的路径都不继续考察了。速度较慢。
public class Solution {

    private int minPathLength;
    // 这个能加速程序的运行速度
    HashMap<String, HashSet<String>> neighborsOfTheStringsInTheDict;

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {

        List<List<String>> shortestPaths = new ArrayList<>();
        if (start == null || end == null || start.length() != end.length()) {
            return shortestPaths;
        }

        minPathLength = Integer.MAX_VALUE;

        dict.add(start);
        dict.add(end);
        
        neighborsOfTheStringsInTheDict = new HashMap<>();

        ArrayList<String> path = new ArrayList<>();
        path.add(start);

        findAllPaths(start, end, dict, path, shortestPaths);
        return shortestPaths;
    }

    private void findAllPaths(String curString, String end,
                              Set<String> dict,
                              ArrayList<String> path,
                              List<List<String>> shortestPaths) {

        if (curString.equals(end)) {
            if (path.size() > minPathLength) {
                return;
            } else if (path.size() < minPathLength) {
                minPathLength = path.size();
                // 清空
                // 注意！！！这里不要用 shortestPaths = new ArrayList<>() 来实现清空！！！
                // 不要 new ！！！那样会造成各种诡异的问题！！！
                shortestPaths.clear(); 
                shortestPaths.add(new ArrayList<String>(path));
            } else { // == minPathLength
                shortestPaths.add(new ArrayList<String>(path));
            }
            return;
        }

        if (path.size() <= minPathLength - 1) {
            
            if (!neighborsOfTheStringsInTheDict.containsKey(curString)) {
                HashSet<String> neighbors = findAllNeighbors(curString, dict);
                neighborsOfTheStringsInTheDict.put(curString, neighbors);
            }
            HashSet<String> allNeighbors = neighborsOfTheStringsInTheDict.get(curString);
            
            for (String neighbor : allNeighbors) {

                if (!path.contains(neighbor)) { // ArrayList 也有 contains 方法！在此可以替代 HashSet 的作用！！！
                    path.add(neighbor);
                    findAllPaths(neighbor, end, dict, path, shortestPaths);
                    path.remove(path.size() - 1); // 复位
                }
            }
        }
    }

    private HashSet<String> findAllNeighbors(String string, Set<String> dict) {

        HashSet<String> neighbors = new HashSet<>();

        for (int i = 0; i < string.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {

                char[] cArray = string.toCharArray();
                if (cArray[i] != c) {
                    cArray[i] = c;
                    String neighbor = new String(cArray);

                    if (dict.contains(neighbor)) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }
}


// 方法2：九章的方法。BFS + DFS。还没看？？？？？？？？？？？？
// Ref: http://www.jiuzhang.com/solutions/word-ladder-ii/
public class Solution {
    public List<List<String>> findLadders(String start, String end,
            Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
 
        bfs(map, distance, start, end, dict);
        
        List<String> path = new ArrayList<String>();
        
        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
            String start, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { 
                    dfs(ladders, path, next, start, distance, map);
                }
            }           
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }
        return expansion;
    }
}

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


// 方法3：九章的方法。BFS + DFS。极为适合装逼的高大上方法 ！！速度比上面的快很多 ！！
// 先从start开始，用 BFS 标定dict里的每个word距start的（最短）距离。包括end距离start的最短距离。那么这样我们就
// 已经知道我们要求的paths的最短长度是多少了
// 然后从end开始，用DFS，找它的邻点里比它距start的距离少1的点，再找邻点的邻点里距start又近了1的点……直到到达start，
// 这样的path可能有不止一个。都记录下来就成了最后的答案
// Ref: http://www.jiuzhang.com/solutions/word-ladder-ii/
public class Solution {

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> shortestPaths = new ArrayList<>();
        
        dict.add(start);
        dict.add(end);
        
        HashMap<String, HashSet<String>> wordsAndNeighbors = new HashMap<>();
        
        HashMap<String, Integer> wordsAndDistsFromStart = new HashMap<>();
        wordsAndDistsFromStart.put(start, 0);

        bfs_GetDists_FromStart(start, wordsAndNeighbors, wordsAndDistsFromStart, dict);
        
        ArrayList<String> path = new ArrayList<>();
        path.add(end);
        dfs_GetPaths_FromEndToStart(end, start, path, wordsAndNeighbors, wordsAndDistsFromStart, shortestPaths);
     
        return shortestPaths; 
    }
    
    // use a Queue to do the BFS Iteratively
    private void bfs_GetDists_FromStart(String start, 
        HashMap<String, HashSet<String>> wordsAndNeighbors,
        HashMap<String, Integer> wordsAndDistsFromStart,
        Set<String> dict) {
        
        Queue<String> managedWords = new LinkedList<>();
        managedWords.offer(start);
        
        while (!managedWords.isEmpty()) {
            String curWord = managedWords.poll();
            int curDistFromStart = wordsAndDistsFromStart.get(curWord);
            
            if (!wordsAndNeighbors.containsKey(curWord)) {
                wordsAndNeighbors.put(curWord, getNeighbors(curWord, dict));
            }
            
            HashSet<String> curNeighbors = wordsAndNeighbors.get(curWord);
            for (String neighbor : curNeighbors) {
                if (!wordsAndDistsFromStart.containsKey(neighbor)) {
                    wordsAndDistsFromStart.put(neighbor, curDistFromStart + 1);
                    managedWords.offer(neighbor);
                }
            }
        }
    }
    
    // Recursively do the DFS to find all shortest paths from end to start
    private void dfs_GetPaths_FromEndToStart(String curString, String start,
        ArrayList<String> path, HashMap<String, HashSet<String>> wordsAndNeighbors,
        HashMap<String, Integer> wordsAndDistsFromStart,
        List<List<String>> shortestPaths) {
        
        if (curString.equals(start)) {
            ArrayList<String> copyPath = new ArrayList<String>(path);
            // 注意！反转 AL 的操作！！
            Collections.reverse(copyPath);
            shortestPaths.add(copyPath);
            return;
        }
        
        int curDist = wordsAndDistsFromStart.get(curString);
        HashSet<String> neighbors = wordsAndNeighbors.get(curString);
        
        // 如果某个neighbor比当前String离start更近了1单位距离，
        // 那么就把这个neighbor放到path里去
        for (String neighbor : neighbors) {
            // 注意！！！
            // 一个String在dict里的合法neighbor，未必出现在连接start与end的图中！！！
            // 所以要先判断一下 wordsAndDistsFromStart.containsKey(neighbor)
            if (wordsAndDistsFromStart.containsKey(neighbor) &&
                wordsAndDistsFromStart.get(neighbor) == curDist - 1) {
                path.add(neighbor);
                dfs_GetPaths_FromEndToStart(neighbor, start, path,
                    wordsAndNeighbors, wordsAndDistsFromStart, shortestPaths);
                path.remove(path.size() - 1);
            }
        }
    }

    private HashSet<String> getNeighbors(String word, Set<String> dict) {
        HashSet<String> neighbors = new HashSet<>();
        
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) != c) {
                    char[] cArray = word.toCharArray();
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

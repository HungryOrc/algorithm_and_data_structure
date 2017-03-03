/* Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
1. Only one letter can be changed at a time
2. Each intermediate word must exist in the dictionary
3. Return 0 if there is no such transformation sequence.
4. All words have the same length.
5. All words contain only lowercase alphabetic characters.

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5. */

public class Solution {
    
    /* @param start, a string
     * @param end, a string
     * @param dict, a set of strings
     * @return an integer */
    public int ladderLength(String start, String end, Set<String> dict) {
        
        if (dict == null || dict.size() == 0 || start.length() != end.length()) {
            return 0;
        }
        
        // 注意！！！
        // 一定要把 start 和 end 放到 dict 里面去！！！虽然他们一开始可能不在dict里
        // 否则，当我们一步一步转化neighbor时，
        // 如果某一个neighbor已经等于end了，但由于它不在dict里，所以它不会被放到queue里去！！！
        // 那么它就根本等不到下一回合它被判定为等于end的那一天了！！！
        dict.add(start);
        dict.add(end);
        
        int steps = 1; // 按题意，start字符串存在的本身就算是第一步了
        Queue<String> stringQueue = new LinkedList<>();
        stringQueue.offer(start);
        HashSet<String> visitedStrings = new HashSet<>();
        
        while (!stringQueue.isEmpty()) {
            int curLayerSize = stringQueue.size();
            
            for (int i = 0; i < curLayerSize; i++) {
                String curString = stringQueue.poll();
                visitedStrings.add(curString);
            
                if (curString.equals(end)) { // equals method means "value equal"
                    return steps;
                }
                
                ArrayList<String> neighborsInDict = findNeighborsInDict(curString, dict);
                for (String neighbor : neighborsInDict) {
                    if (!visitedStrings.contains(neighbor)) {
                        stringQueue.offer(neighbor);
                        visitedStrings.add(neighbor);
                    }
                }
                
            }
            steps ++;
        }
        
        return 0; // if we cannot reach the target string eventually
    }
    
    private ArrayList<String> findNeighborsInDict(String curString, Set<String> dict) {
        ArrayList<String> neighbors = new ArrayList<>();
        
        for (int i = 0; i < curString.length(); i++) {
            
            // 注意！！char 从 'a' 到 'z' 的循环实现方式如下！！
            for (char c = 'a'; c <= 'z'; c++) {
                
                char[] charArray = curString.toCharArray();
                if (charArray[i] != c) { // 这是为了不要把和自己完全一样的String也放进去了
                    charArray[i] = c;
                    
                    String neighbor = new String(charArray);
                
                    // check if this neighbor String exists in the Dictionary
                    if (dict.contains(neighbor)) {
                        neighbors.add(neighbor);
                    }
                }         
            }
        }
        return neighbors;
    }
    
}

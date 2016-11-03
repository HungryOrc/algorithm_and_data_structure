/* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.
Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

public class Solution {

    // 把2个字分别挑出来形成两个ArrayList，带上各自的位置作为int值存在两个ArrayList里，再进行两两比较
    // Time: O(n^2). Space: O(1), since no additional space is used
    public int shortestDistance(String[] words, String word1, String word2) {
        
        ArrayList<Integer> occuranceWord1 = new ArrayList<>();
        ArrayList<Integer> occuranceWord2 = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++)
        {
            if (words[i].equals(word1))
                occuranceWord1.add(i);
            else if (words[i].equals(word2))
                occuranceWord2.add(i);
        }
        
        int minDist = words.length - 1;
        for (int i = 0; i < occuranceWord1.size(); i++)
        {
            for (int j = 0; j < occuranceWord2.size(); j++)
            {
                if (Math.abs(occuranceWord1.get(i) - occuranceWord2.get(j)) < minDist)
                    minDist = Math.abs(occuranceWord1.get(i) - occuranceWord2.get(j));
            }
        }
        return minDist;
    }
    
    
    // One Pass
    // Ref: https://leetcode.com/articles/shortest-word-distance/
    /* Keep 2 indices i1 and i2 where we store the most recent locations of word1 and word2. 
     Each time we find a new occurrence of one of the words, we do not need to search the entire array for the other word, 
     since we already have the index of its most recent occurrence.
     Time: O(n). Space: O(1).
    */
    public int shortestDistance_OnePass(String[] words, String word1, String word2) {
        
        int i1 = -1, i2 = -1;
        int minDist = words.length - 1;
        boolean indexChanged = false;
        for (int i = 0; i < words.length; i ++)
        {
            if (words[i].equals(word1))
            {
                i1 = i;
                indexChanged = true;
            }
            else if (words[i].equals(word2))
            {
                i2 = i;
                indexChanged = true;
            }
            
            if (indexChanged && i1 != -1 && i2 != -1)
            {
                minDist = Math.min(minDist, Math.abs(i1-i2));
                indexChanged = false;
            }
        }
        return minDist;
    }
    
}

/* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.
Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

public class Solution {

    // 把2个字分别挑出来形成两个ArrayList，带上各自的位置作为int值存在两个ArrayList里，再进行两两比较
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
}

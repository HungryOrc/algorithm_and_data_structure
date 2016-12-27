/* Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input: "tree"
Output: "eert" or "eetr"

Example 2:
Input: "cccaaa"
Output: "cccaaa" or "aaaccc"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input: "Aabb"
Output: "bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters. */

public class Solution 
{
    // 我的一种朴素做法。速度还可以
    // 注意1：HashMap 的 EntrySet
    // 注意2：sort ArrayList，把里面的 Comparator 里面的 compare 方法做一个 Override
    public String frequencySort(String s) 
    {
        if (s==null || s.length()<=1)
            return s;
        
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray())
            charMap.put(c, charMap.getOrDefault(c,0)+1);
        
        ArrayList<Map.Entry<Character,Integer>> charAL = new ArrayList<>();
        for (Map.Entry<Character,Integer> entry : charMap.entrySet())
            charAL.add(entry);
        
        // sort the ArrayList by the count of each char, from higher count to lower
        Collections.sort(charAL, new Comparator<Map.Entry<Character,Integer>>()
        {
            @Override
            public int compare(Map.Entry<Character,Integer> entry1,
                               Map.Entry<Character,Integer> entry2)
            {
                // 要从大到小！所以一反常态，前者比后者大反而要 return -1
                if (entry1.getValue() > entry2.getValue())
                    return -1;
                else if (entry1.getValue() < entry2.getValue())
                    return 1;
                else // equal
                    return 0;
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charAL.size(); i++)
        {
            char c = charAL.get(i).getKey(); // 要填入的char
            int count = charAL.get(i).getValue(); // 本char被填入的次数
            for (int j = 1; j <= count; j++)
                sb.append(c);
        }
        return sb.toString();
    }
}

/* Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space. */

public class Solution
{
    // 最朴素的方法：设立两个HashMap，同步逐步构建这两个Map，
    // 一旦这两个Map中的任何一对相应的ArrayList互不相等，则false。如果成功走到最后，则true
    public boolean wordPattern(String pattern, String str)
    {
        String[] strSplitted = str.split(" ");
        if (strSplitted.length != pattern.length()) // 如果二者长度不等
            return false;
            
        HashMap<Character, ArrayList<Integer>> patternMap = new HashMap<>();
        HashMap<String, ArrayList<Integer>> strMap = new HashMap<>();
            
        // 把pattern 里相同的char的indexes，放到同一个ArrayList 里去
        // 把strSplitted 里相同的String的indexes，也放到另外一个同一个ArrayList里去
        // 然后比较这两个ArrayList
        
        for (int i = 0; i < pattern.length(); i++)
        {
            char curChar = pattern.charAt(i);
            ArrayList<Integer> curCharIndexes = new ArrayList<>();
            String curString = strSplitted[i];
            ArrayList<Integer> curStringIndexes = new ArrayList<>();
            
            // 处理 curChar 的 ArrayList
            if (patternMap.containsKey(curChar))
            {
                curCharIndexes = patternMap.get(curChar);
                curCharIndexes.add(i);
                patternMap.put(curChar, curCharIndexes);
            }
            else
            {
                curCharIndexes.add(i);
                patternMap.put(curChar, curCharIndexes);
            }
            
            // 处理 curString 的 ArrayList
            if (strMap.containsKey(curString))
            {
                curStringIndexes = strMap.get(curString);
                curStringIndexes.add(i);
                strMap.put(curString, curStringIndexes);
            }
            else
            {
                curStringIndexes.add(i);
                strMap.put(curString, curStringIndexes);
            }
            
            // 比较这两个ArrayList。注意！！！
            // 使用 equals() 方法来判定2个 reference type 数据类型之间的 “值相等” 与否！！！
            if (!curCharIndexes.equals(curStringIndexes))
                return false;
        }
        return true;
    }
}

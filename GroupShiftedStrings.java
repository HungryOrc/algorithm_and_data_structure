/* Given a string, we can "shift" each of its letter to its successive letter, 
for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
"yza" -> "zab" -> "abc" -> ... -> "xyz"

Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/

public class Solution {
    
    public List<List<String>> groupStrings(String[] strings) {
        
        HashMap<String, ArrayList<String>> mapOfShiftedStrings = new HashMap<>();
    
        for (int i = 0; i < strings.length; i++)
        {
            String curString = strings[i];
            char[] curCharArray = curString.toCharArray();
            char[] curShiftedCharArray = new char[curCharArray.length];
            
            int curShift = curCharArray[0] - 'a';
            for (int j = 0; j < curCharArray.length; j++)
            {
                if (curCharArray[j] - 'a' < curShift)
                    /* 运算符 + 和 - 作用在2个char之间，得到的是 int！
                     作用在 char 和 int 之间，得到的也是 int！
                     所以下式必须在最外层做一个 (char) 强制类型转换！*/
                    curShiftedCharArray[j] = (char)(curCharArray[j] + 26 - curShift);
                else
                    curShiftedCharArray[j] = (char)(curCharArray[j] - curShift);
            }
            String curShiftedString = new String(curShiftedCharArray);
            
            ArrayList<String> correspondingArrayList = 
                mapOfShiftedStrings.getOrDefault(curShiftedString, new ArrayList<String>());
            correspondingArrayList.add(curString);
            mapOfShiftedStrings.put(curShiftedString, correspondingArrayList);
        }
        
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        for (ArrayList<String> curArrayList : mapOfShiftedStrings.values())
            result.add(curArrayList);
        return result;
    }
}

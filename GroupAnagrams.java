/* Given an array of strings, group anagrams together.
For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:
[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case. */

// 我的方法。思路很自然直接。速度不算快
public class Solution {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        
        HashMap<String, ArrayList<String>> recordOfLetters = new HashMap<>();
        
        for (String s : strs) {
            int[] records = new int[26];
            for (char c : s.toCharArray()) {
                records[c - 'a'] ++;
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (records[i] > 0) {
                    sb.append(i + ":");
                    sb.append(records[i] + "_");
                }
            }
            String recordString = sb.toString();
            
            if (recordOfLetters.containsKey(recordString)) {
                recordOfLetters.get(recordString).add(s);
            } else {
                ArrayList<String> al = new ArrayList<>();
                al.add(s);
                recordOfLetters.put(recordString, al);
            }
            
        }
        
        for (ArrayList<String> al : recordOfLetters.values()) {
            result.add(al);
        }
        return result;
    }
}

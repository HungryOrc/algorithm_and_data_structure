/* Remove given characters in input string, the relative order of other characters should be remained. 
Return the new string after deletion.

Assumptions
The given input string is not null.
The characters to be removed is given by another string, it is guranteed to be not null.

Examples
input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd". */

// 要求 in place ！！！
// 方法：两个指针一快一慢。也可理解为两个挡板，其实是一样的
public class Solution {
  
  public String remove(String input, String t) {
    
    HashSet<Character> toBeRemoved = new HashSet<>();
    for (char c : t.toCharArray()) {
      toBeRemoved.add(c);
    }
    
    char[] cArray = input.toCharArray();
    int index = 0;    
    for (int i = 0; i < input.length(); i++) {
      if (!toBeRemoved.contains(input.charAt(i))) {
        cArray[index] = cArray[i];
        index ++;
      }
    }
    
    // index在最后还会自增1，所以这里的上限用index即可
    return new String(cArray, 0, index);
  }
  
}

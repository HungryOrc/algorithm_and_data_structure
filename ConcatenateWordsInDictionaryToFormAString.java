/* Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.
Assumptions:
The given word is not null and is not empty
The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty

Examples:
Dictionary: {“bob”, “cat”, “rob”}
Word: “robob” return false
Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"   */


// DP
// dp数组里 dp[i] 的意思是，给的String从index 0 开始到 index i 为止包含 index i 在内，是否能用字典里的words组成
// 我们一开始从只有一个字母开始，即dp[0]；然后扩展到2个字母、3个字母……最后返回的是 dp[input.length() - 1]。
// Time: O(n^3)。因为2层for循环，再加上最里面的substring函数是O(n)的时间消耗
public class Solution {
  
  public boolean canBreak(String input, String[] dict) {
    HashSet<String> dictionary = new HashSet<>();
    for (String s : dict) {
      dictionary.add(s);
    }
    
    boolean[] dp = new boolean[input.length()];
    dp[0] = dictionary.contains(input.substring(0, 1)) ? true : false;
    
    for (int i = 1; i < input.length(); i++) {
      
      // if the word from 0 to i is in the dictionary, done
      if (dictionary.contains(input.substring(0, i + 1))) {
        dp[i] = true;
      }
      // else, we divide the word from 0 to i into two parts:
      // first part: 0 to j,
      // we check the dp array for index j;
      // second part: j + 1 to i,
      // we check if the word from j + 1 to i is in the dictionary
      else {
        for (int j = 0; j < i; j++) {
          if (dp[j] == true && 
            dictionary.contains(input.substring(j + 1, i + 1))) {
            dp[i] = true;
            break;
          }
        }
        // if dp[i] is never changed to true during this for-loop,
        // then it will stay in its default value: false
      }
    }
    return dp[input.length() - 1];
  }
}

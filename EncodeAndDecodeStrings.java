/* Design an algorithm to encode a list of strings to a string. 
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}

Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}

So Machine 1 does:
string encoded_string = encode(strs);
and Machine 2 does:
vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Note:
The string may contain any possible characters out of 256 valid ascii characters. 
Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm. */


// 这一题的解法都蕴含了很重要的  思  维  方  式 ！！！


// 方法1：用每个String的长度（数字）+ 特定分隔符，来分割各个Strings
// 注意！！！就算某个String里出现了上述的模式，也会被正确处理，因为从第一步开始，每一步前进多长，已经被这种编码方法所唯一地定死了！！！
// Ref: https://discuss.leetcode.com/topic/22848/ac-java-solution
public class Codec {
    
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        while(i < s.length()) {
            int slash = s.indexOf('/', i);
            int size = Integer.valueOf(s.substring(i, slash));
            ret.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;
        }
        return ret;
    }
}


// 方法2：选用一种分隔符，然后把原Strings里面出现的所有此分隔符全部就地double
// Ref: https://discuss.leetcode.com/topic/24013/java-with-escaping
/* Double any hashes inside the strings, then use standalone hashes (surrounded by spaces) to mark string endings. For example:
{"abc", "def"}    =>  "abc # def # "
{'abc', '#def'}   =>  "abc # ##def # "
{'abc##', 'def'}  =>  "abc#### # def # "
For decoding, just do the reverse: First split at standalone hashes, then undo the doubling in each string. */
public String encode(List<String> strs) {
    StringBuffer out = new StringBuffer();
    for (String s : strs)
        out.append(s.replace("#", "##")).append(" # ");
    return out.toString();
}

public List<String> decode(String s) {
    List strs = new ArrayList();
    String[] array = s.split(" # ", -1);
    for (int i=0; i<array.length-1; ++i)
        strs.add(array[i].replace("##", "#"));
    return strs;
}

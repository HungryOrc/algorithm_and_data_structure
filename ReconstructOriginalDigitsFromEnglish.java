/* Given a non-empty string containing an out-of-order English representation of digits 0-9, 
output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. 
That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.

Example 1:
Input: "owoztneoer"
Output: "012"

Example 2:
Input: "ffuviorefuro"
Output: "445"

很巧妙的方法，我没想到
Ref: https://leetcode.com/problems/reconstruct-original-digits-from-english/
The idea is:
for zero, it's the only word has letter 'z',
for two, it's the only word has letter 'w',
......
so we only need to count the unique letter of each word, Coz the input is always valid. */

public class Solution 
{
    public String originalDigits(String s) 
    {
        int[] count = new int[10];
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7和6一共的出现次数
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++; 
            if (c == 'f') count[5]++; //5和4一共的出现次数
            if (c == 'h') count[3]++; //3和8一共的出现次数
            if (c == 'i') count[9]++; //9，8，5，6一共的出现次数
            if (c == 'o') count[1]++; //1，0，2，4一共的出现次数
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++) // 数字1到9
        {
            for (int j = 1; j <= count[i]; j++) // 各自出现了多少次
                sb.append(i);
        }
        return sb.toString();
    }
}

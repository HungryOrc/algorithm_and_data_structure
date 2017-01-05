/* Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Roman Numerals Chart:

I      1   |	X      10  |	C      100  |	M          1000
II     2   |	XX     20  |	CC     200  |	M M        2000
III    3   |	XXX    30  |	CCC    300  |	M M M      3000
IV     4   |	XL     40  |	CD     400  |	M M M M    4000
V      5   |	L      50  |	D      500  |	M M M M M  5000
VI     6   |	LX     60  |	DC     600  |	etc...
VII    7   |	LXX    70  |	DCC    700  |
VIII   8   |	LXXX   80  |	DCCC   800  |
IX     9   |	XC     90  |	CM     900  |
*/

public class Solution
{
   // 从前往后，把每个罗马字母转换为一个数，如果后一个数比前一个大则减前一个数，否则为加前一个数
   // Ref: https://discuss.leetcode.com/topic/28471/7ms-solution-in-java-easy-to-understand
   public int romanToInt (String s)
   {
      int nums[] = new int[s.length()];
      for (int i = 0; i < s.length(); i++)
      {
         switch (s.charAt(i))
         {
            case 'M':
               nums[i] = 1000;
               break;
            case 'D':
               nums[i] = 500;
               break;
            case 'C':
               nums[i] = 100;
               break;
            case 'L':
               nums[i] = 50;
               break;
            case 'X':
               nums[i] = 10;
               break;
            case 'V';
               nums[i] = 5;
               break;
            case 'I':
               nums[i] = 1;
               break;
         }
      }
      int sum = 0;
      for (int i = 0; i < nums.length - 1; i++)
      {
         if (nums[i] < nums[i+1])
            sum -= nums[i];
         else
            sum += nums[i];
      }
      return sum + nums[nums.length-1]; // 别忘了加最后的一个数！
   }
   
   
   // 从后往前处理，即最先个位，然后十位，然后百位... 每处理一位就加和
   // 当和大于等于5而再往上一位又出现 I 的话，那么这个 I 要减去。以此类推。很巧妙！
   // Ref: https://discuss.leetcode.com/topic/821/my-solution-for-this-question-but-i-don-t-know-is-there-any-easier-way
   public int romanToInt (String s)
   {
      int sum = 0;
      for (int i = s.length()-1; i >= 0; i--)
      {
         char c = s.charAt(i);
         switch (c)
         {
            case 'I':
               sum += (sum>=5 ? -1 : 1);
               break;
            case 'V':
               sum += 5;
               break;
            case 'X':
               sum += 10 * (sum>=50 ? -1 : 1);
               break;
            case 'L':
               sum += 50;
               break;
            case 'C':
               sum += 100 * (sum>=500 ? -1 : 1);
               break;
            case 'D':
               sum += 500;
               break;
            case 'M':
               sum += 1000;
               break;
         }
      }
      return sum;
   }
   
   
   // 从左到右逐个位数加和。最后将所有可能出现的该减去的情况减去，注意每一种该减去的情况最多只会出现一次！！要么出现，要么不出现
   // Ref: https://discuss.leetcode.com/topic/821/my-solution-for-this-question-but-i-don-t-know-is-there-any-easier-way
   public int romanToInt (String s)
   {
      int sum = 0;
      char[] charArray = s.toCharArray();
      for (char c : charArray)
      {
         if (c == 'M')
            sum += 1000;
         else if (c == 'D')
            sum += 500;
         else if (c == 'C')
            sum += 100;
         else if (c == 'L')
            sum += 50;
         else if (c == 'X')
            sum += 10;
         else if (c == 'V')
            sum += 5;
         else if (c == 'I')
            sum += 1;
      }
      
      if (s.indexOf("IV") != 1)
         sum -= 2;
      if (s.indexOf("IX") != 1)
         sum -= 2;
      if (s.indexOf("XL") != 1)
         sum -= 20;
      if (s.indexOf("XC") != 1)
         sum -= 20;
      if (s.indexOf("CD") != 1)
         sum -= 200;
      if (s.indexOf("CM") != 1)
         sum -= 200;
      
      return sum;   
   }
   
   
}

/* Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Roman Numerals Chart:

I	    1   |	X	    10  |	C	    100  |	M	         1000
II	  2   |	XX	  20  |	CC	  200  |	MM	       2000
III	  3   |	XXX	  30  |	CCC	  300  |	M M M	     3000
IV	  4   |	XL	  40  |	CD	  400  |	M M M M	   4000
V	    5   |	L     50  |	D	    500  |	M M M M M  5000
VI	  6   |	LX	  60  |	DC	  600  |	etc...
VII	  7   |	LXX	  70  |	DCC	  700  |
VIII	8   |	LXXX	80  |	DCCC	800  |
IX	  9   |	XC	  90  |	CM	  900  |
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
   
   
   
   
}

/* Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.
For example:
Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity? */

// Ref: https://leetcode.com/problems/single-number-iii/
// 很巧妙的方法，接近刁钻，反正是我没想到

/* Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:

In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. 
Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. 
Find out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, 
another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups. 
XOR numbers in each group, we can find a number in either group.

Complexity:
Time: O (n)
Space: O (1)

A Corner Case:
When diff == numeric_limits<int>::min(), -diff is also numeric_limits<int>::min(). 
Therefore, the value of diff after executing diff &= -diff is still numeric_limits<int>::min(). The answer is still correct. */

public class Solution 
{
    public int[] singleNumber(int[] nums) 
    {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        // 注意这种方法！能取到diff这个数里最右边的一个1，然后其它位全部变成0
        // remember，一个数的负数是这个数 取反 再 加一
        diff &= -diff;
        // diff的这一位为1，表明我们要找的2个数里，一个在这一位为1，一个在这一位为0
        // 因为diff是这两个数的XOR
        
        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
                // 所有的在diff的为1的那一位为0的数，连续地进行XOR
                // 最终将得到我们要的两个数中的一个，这一个在那一位是0
                rets[0] ^= num;

            else // the bit is set
                // 所有的在diff的为1的那一位也为1的数，连续地进行XOR
                // 最终将得到我们要的两个数中的另一个，这一个在那一位是1
                rets[1] ^= num;
        }
        return rets;
    }
}

/* Write an algorithm to determine if a number is "happy".
A happy number is a number defined by the following process: Starting with any positive integer, 
replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
Example: 19 is a happy number
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1  */

public class Solution {

    // Ref: https://discuss.leetcode.com/topic/25026/beat-90-fast-easy-understand-java-solution-with-brief-explanation
    /* The idea is to use one hash set to record sum of every digit square of every number occurred. 
     Once the current sum cannot be added to set (意味着有重复了，即意味着从此开始进入loop了), 
     return false; once the current sum equals 1, return true;
    */
    public boolean isHappy(int n) {
        
        HashSet<Integer> occuredSums = new HashSet<>();
        
        int sums = 0;
        while (occuredSums.add(n)) // 这个换成 !occuredSums.contains(n) 应该也行
        {
            while (n != 0)
            {
                sums += (n % 10)*(n % 10);
                n = n / 10;
            }
            
            if (sums == 1)
                return true;
            else 
            {
                n = sums;
                sums = 0;
            }
        }
        
        return false;
    }
}

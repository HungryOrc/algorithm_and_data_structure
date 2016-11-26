/* Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1)
If you are thinking of converting the integer to string, note the restriction of using extra space.
You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?
There is a more generic way of solving this problem.

// Ref: https://discuss.leetcode.com/topic/8090/9-line-accepted-java-code-without-the-need-of-handling-overflow/10
public class Solution {
    
    // 考察x一半的位数即可
    // 不断地取它的个位、十位……同时不断地将它除以十
    public boolean isPalindrome(int x) {
        
        // 特别注意！！||后面的处理
        // 这是为了应对 x=10 或者其它的个位为0的情况的情况。如果这里不这么写，则下面的代码无法应对
        if (x < 0 || (x!=0&&x%10==0))
            return false;
            
        int reverse = 0;
        while (x > reverse)
        {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        // x==reverse是应对x原本有偶数位的情况；x==reverse/10是x原本有奇数位的情况
        return (x == reverse || x == reverse/10);
    }
}

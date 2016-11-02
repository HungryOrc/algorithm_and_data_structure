/* Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
For example: Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
Follow up: Could you do it without any loop/recursion in O(1) runtime?

(1) 能被9整除的数，其各位数之和也能被9整除。证明：一个多位数abcd = a*999 + b*99 + c*9 + d + (a+b+c+d)，由此易见结论。
(2) 一个能被9整除的数x的各位数之和y也能被9整除，所以y的各位数之和z也能被9整除，
如此下去，可以想见经过多次这样的处理后，最后各位数之和收缩为9，且不再变化。
(3) 对于x，它的任一位上的数字+1的话。如果被加的那一位不是9，则y也会跟着+1。如果被加的那一位是9，则y-9+1，因为当位由9变0，前一位+1。
如果被加的那一位是9且紧邻它的m个高位都是9，则y-9*m+1。所以新的y除以9余1。设y=9*n+1。
(4) 将y分解为9n和1这两部分。按照步骤(2)的精神进行分析，可以得到y的各位数之和，经过多次加和后，将收缩为10，继而1，且不再变化。
(5) 由此可以想见，一个数等于9n+s的话，那么它的各位数之和，经过多次加和后，将收缩为s，且不再变化。
*/

public class Solution {
    
    public int addDigits_ByMod9(int num) {
        
        if (num == 0)
            return 0;
        else if (num % 9 == 0)
            return 9;
        else 
            return num % 9;
    }
    
    
    // 傻大黑粗的暴力直接解法，并运用了 Recursion
    public int addDigits(int num) {
        
        if (num == 0)
            return 0;
        int sum = 0, numLeft = num;
        
        while(numLeft != 0)
        {
            int y = numLeft % 10;
            numLeft = numLeft / 10;
            sum += y;
        }
    
        if (sum > 9)
            sum = addDigits(sum);
        
        return sum;
    }
    
}

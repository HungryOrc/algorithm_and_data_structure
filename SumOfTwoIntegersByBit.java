// Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -

// Reference: http://stackoverflow.com/questions/9070937/adding-two-numbers-without-operator-clarification
/* Think bit by bit addition, there are only 4 cases:
0+0=0 
0+1=1 
1+0=1 
1+1=0 (and generates carry (进位))

So we can understand this equation: a + b = (a^b) + (a&b)<<1
It has 2 parts:
a ^ b
Handles case 0+1 and 1+0, namely all bit positions that add up to 1.
(a & b) << 1
The (a & b) part finds all bit positions with the case 1+1 and 0+0. 
for 0+0 = 0, so that's done. 
for 1+1 = 10, this addition results in 0 in the original digit, and a carry to the higher digit, 
so we can manage it as it's shifted to the next position to the left (<<1). 
The carry needs to be added to that position, so the algorithm runs again.

The algorithm repeats until there are no more carries.   */

public class Solution {
    
    // by Recursion
    public int getSum(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        int sumOfOneAndZeroByBit = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sumOfOneAndZeroByBit, carry);
    }
    
    // by Iteration
    public int getSum(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        while (b != 0)
        {
            int carryBeforeShift = a & b;
            a = a ^ b;
            b = carryBeforeShift << 1;
        }
        return a;
    }
    
    
    // 以下三个没有细看，以后再看：
    // Iterative Subtraction
    public int getSubtract(int a, int b) {
      while (b != 0) {
        int borrow = (~a) & b;
        a = a ^ b;
        b = borrow << 1;
      }

      return a;
    }

    // Recursive Addition
    public int getSum(int a, int b) {
      return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
    }

    // Recursive Subtraction
    public int getSubtract(int a, int b) {
      return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
    }
    
    
}

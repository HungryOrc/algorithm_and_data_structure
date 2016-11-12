/* Write a program to check whether a given number is an ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
Note that 1 is typically treated as an ugly number, while 0 is not. */

public class Solution {
    
    public boolean isUgly(int num) {
        
        if (num == 1)
            return true;
        if (num == 0)
            return false;
            
        // 注意！用while对于一种因数（比如2或3或5）一除到底比较快，每一次都除一个2除一个3再除一个5的话比较慢
        while (num % 2 == 0)
            num /= 2;
        while (num % 3 == 0)
            num /= 3;
        while (num % 5 == 0)
            num /= 5;
        
        if (num == 1)
            return true;
        else
            return false;
    }
}

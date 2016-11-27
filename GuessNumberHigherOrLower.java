/* We are playing the Guess Game:
I pick a number from 1 to n. You have to guess which number I picked.
Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
-1 : The picked number is lower than num
 1 : The picked number is higher than num
 0 : Congrats! You got it!
 
Example:
n = 10, I pick 6.
Return 6, which means to find the number that I picked. */

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num);
   n is the upper bound of the number picked, namely the number had been picked within [1, n].
*/
public class Solution extends GuessGame {
    
     // Iteration, Binary Search
     public int guessNumber(int n) {
        
        int lowerBound = 1;
        int upperBound = n;
        
        // while 往往能化 Recursion 为 Iteration ！！！！
        while(lowerBound <= upperBound) 
        {
            int mid = (lowerBound+upperBound)/2; // 这样能减少运算次数！
            int guessResult = guess(mid); // 这样能减少运算次数！
            
            if (guessResult == 0)
                return mid;
            else if (guessResult == -1)
                upperBound = mid - 1;
            else // guessResult == 1
                lowerBound = mid + 1;
        }
        return -1; // 如果一定能找到的话，即pickedNum一定在1-n之间的话，则永远不会到这一步来
    }
 
    
    // Ternary Search，三分法
    // Ref: https://leetcode.com/articles/guess-number-higher-or-lower/
    // 三分法在一些情况下会比二分法更快，但关键是它的worst case 会比二分法的worst case 更慢！！
    public int guessNumber(int n)
    {
        int low = 1;
        int high = n;
        while (low <= high)
        {
            // 三分法的关键在这里！！
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
 
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            
            if (res1 == 0)
                return mid1;
            else if (res2 == 0)
                return mid2;
            else if (res1 < 0)
                high = mid1 - 1;
            else if (res2 > 0)
                low = mid2 + 1;
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }
 
 
    // Recursion 方法
    public int guessNumber(int n) {
        return find(1, n);
    }
    private int find(int lowerBound, int upperBound)
    {
        int mid = (lowerBound+upperBound)/2; // 这样能减少运算次数！！
        int guessResult = guess(mid); // 这样能减少运算次数！
     
        if (guessResult == 0)
            return mid;
        else if (guessResult == -1)
            return find(lowerBound, mid-1);
        else // guessResult == 1
            return find(mid+1, upperBound);
    }
 
 
}

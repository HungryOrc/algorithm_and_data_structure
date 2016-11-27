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

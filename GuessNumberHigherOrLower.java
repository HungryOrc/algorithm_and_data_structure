/* We are playing the Guess Game:
I pick a number from 1 to n. You have to guess which number I picked.
Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
-1 : My number is lower
 1 : My number is higher
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
    
    // Recursion 方法
    public int guessNumber(int n) {
        return find(1, n);
    }
    private int find(int lowerBound, int upperBound)
    {
        if (guess((lowerBound+upperBound)/2) == 0)
            return (lowerBound+upperBound)/2;
        else if (guess((lowerBound+upperBound)/2) == 1)
            return find(lowerBound, (lowerBound+upperBound)/2-1);
        else // guess((lowerBound+upperBound)/2) == -1
            return find((lowerBound+upperBound)/2+1, upperBound);
    }
}

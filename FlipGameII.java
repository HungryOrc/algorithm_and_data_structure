/* You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.
For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up: Derive your algorithm's runtime complexity. */

public class Solution 
{
    /* We can basically try every possible move for the first player (Let's call him 1P from now on), 
    and recursively check if the second player 2P has any chance to win. 
    If 2P is guaranteed to lose, then we know the current move 1P takes must be the winning move.
    
    另一方面：For the time complexity, here is what I thought, let's say the length of the input string s is n, 
    there are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), 
    once we replace one "++", there are at most (n - 2) - 1 ways to do the replacement, 
    it's a little bit like solving the N-Queens problem, the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., 
    so it's O(n!!), double factorial. */
    public boolean canWin(String s) 
    {
        if (s == null || s.length() < 2)
            return false;
        
        for (int i = 0; i < s.length() - 1; i++) 
        {
            if (s.startsWith("++", i)) 
            {
                String sub = s.substring(0, i) + "--" + s.substring(i + 2);
                // 假设双方棋手都是充分明智的
                // 如果所有的substring中，有任何一个能导致对方不可能赢，则我方必赢
                // 如果所有的substring中，对方都有赢的可能性，则我方必输。此即最后for以外的return false的情况
                if (!canWin(sub))
                    return true;
            }
        }
        return false;
    }
}

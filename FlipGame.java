/* You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to compute all possible states of the string after one valid move.
For example, given s = "++++", after one move, it may become one of the following states:
[
  "--++",
  "+--+",
  "++--"
]
*/

public class Solution {
    
    // 最普通的方法
    public List<String> generatePossibleNextMoves(String s) {
        char[] charArray = s.toCharArray();
        List<String> output = new ArrayList<String>();
        
        for (int i = 0; i < charArray.length-1; i++)
        {
            if (charArray[i] == '+' && charArray[i+1] == '+')
            {
                // 这里一定要新做一个 charArray
                char[] tempCharArray = s.toCharArray();
                tempCharArray[i] = '-';
                tempCharArray[i+1] = '-';
         
                output.add(new String(tempCharArray));
            }
        }
        return output;
    }
}

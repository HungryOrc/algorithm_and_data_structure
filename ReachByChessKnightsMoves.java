/* Write a function called answer(src, dest) which takes in two parameters: 
the source square, on which you start, and the destination square, which is where you need to land to solve the puzzle.  
The function should return an integer representing the smallest number of moves it will take for you to 
travel from the source square to the destination square using a chess knight's moves 
(that is, two squares in any direction immediately followed by one square perpendicular to that direction, 
or vice versa, in an "L" shape).  
Both the source and destination squares will be an integer between 0 and 63, 
inclusive, and are numbered like the example chessboard below:

-------------------------
| 0| 1| 2| 3| 4| 5| 6| 7|
-------------------------
| 8| 9|10|11|12|13|14|15|
-------------------------
|16|17|18|19|20|21|22|23|
-------------------------
|24|25|26|27|28|29|30|31|
-------------------------
|32|33|34|35|36|37|38|39|
-------------------------
|40|41|42|43|44|45|46|47|
-------------------------
|48|49|50|51|52|53|54|55|
-------------------------
|56|57|58|59|60|61|62|63|
-------------------------

Example:

Inputs:
    (int) src = 19
    (int) dest = 36
Output:
    (int) 1

Inputs:
    (int) src = 0
    (int) dest = 1
Output:
    (int) 3
*/

import java.util.*;

public class Answer 
{   
    public static int answer(int src, int dest) 
    { 
        if (src == dest)
            return 0;
            
        HashSet<Integer> visitedSlots = new HashSet<Integer>();
        Queue<Integer> manageSlots = new LinkedList<Integer>();
        
        manageSlots.addAll(findNextStep(src));
        int curStep = 1;
        while(!manageSlots.isEmpty())
        {
            int remainingNum = manageSlots.size();
            while (remainingNum > 0)
            {
                int curSlot = manageSlots.poll();
                remainingNum--;
                if (curSlot == dest)
                    return curStep;
                if (!visitedSlots.contains(curSlot))
                {
                    visitedSlots.add(curSlot);
                    manageSlots.addAll(findNextStep(curSlot));
                }
            }
            curStep ++;
        }
        return -1;
    } 
    
    private static ArrayList<Integer> findNextStep(int index)
    {
        int curRow = indexToCoord(index)[0];
        int curCol = indexToCoord(index)[1];
        
        int[][] directions = {
            {2,1},{2,-1},{-2,1},{-2,-1},
            {1,2},{1,-2},{-1,2},{-1,-2},
        };
    
        ArrayList<Integer> validDests = new ArrayList<Integer>();
        for (int[] move : directions)
        {
            int desRow = curRow + move[0];
            int desCol = curCol + move[1];
            
            if (desRow >= 1 && desRow <= 8 &&
                desCol >= 1 && desCol <= 8)
                validDests.add(coordToIndex(desRow,desCol));
        }
        return validDests;
    }
    
    private static int[] indexToCoord (int index)
    {
        int[] coord = new int[2];
        coord[0] = index / 8 + 1;
        coord[1] = index % 8 + 1;
        return coord;
    }
    
    private static int coordToIndex (int row, int col)
    {
        return (row-1) * 8 + (col-1);
    }   
}

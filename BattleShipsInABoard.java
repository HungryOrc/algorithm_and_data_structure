/* Given an 2D board, count how many different battleships are in it. Your algorithm should not modify the value of the board.
The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.   */

public class Solution {

    // 一般的办法：一行一行从左到右看，遇到 'X' 则把它右方或者下方相连的所有X都标为visited，
    // 凡是 '.' 或者 visited 'X' 都不算。各个所谓首次遇到的 'X' 处都把 shipCount ++
    // 注意：String 用 ""， char 用 ''
    //
    public int countBattleships_ByEachCell(char[][] board) {
        
        int rowCount = board.length;
        int colCount = board[0].length;
        int shipCount = 0;
        // The default value for the elements in a boolean[] is false
        boolean[][] visited = new boolean[rowCount][colCount];
        
        for (int i = 0; i < rowCount; i++)
        {
            for (int j = 0; j < colCount; j++)
            {
                // 注意！String 用 ""，char 用 ''
                if (visited[i][j] == true || board[i][j] == '.')
                    continue;
                    
                if (board[i][j] == 'X')
                {
                    visited[i][j] = true;
                    shipCount ++;
                    
                    if (j+1 < colCount && board[i][j+1] == 'X')
                    {
                        int curCol = j+1;
                        while (curCol < colCount && board[i][curCol] == 'X')
                        {
                            visited[i][curCol] = true;
                            curCol ++;
                        }
                    }
                    else if (i+1 < rowCount && board[i+1][j] == 'X')
                    {
                        int curRow = i+1;
                        while (curRow < rowCount && board[curRow][j] == 'X')
                        {
                            visited[curRow][j] = true;
                            curRow ++;
                        }
                    }
                }
            }
        }
        return shipCount;
    }
    
    
    /* 巧妙办法：找每一个“最左上”的 'X'
    Reference: https://discuss.leetcode.com/topic/62970/simple-java-solution
    Going over all cells, we can count only those that are the "FIRST" cell of the battleship. 
    First cell will be defined as the most top-left cell. We can check for first cells by 
    only counting cells that do not have an 'X' to the left and do not have an 'X' above them.   */
    //
    public int countBattleships_ByMostTopLeftX(char[][] board) {
    
        int rowCount = board.length;
        if (rowCount == 0) 
           return 0;
           
        int colCount = board[0].length;
        
        int shipCount=0;
        
        for (int i=0; i<rowCount; i++) {
            for (int j=0; j<colCount; j++) {
                if (board[i][j] == '.') 
                   continue;
                if (i > 0 && board[i-1][j] == 'X') 
                   continue;
                if (j > 0 && board[i][j-1] == 'X')
                   continue;
                
                shipCount++;
            }
        }
        return shipCount;
    }
    
}

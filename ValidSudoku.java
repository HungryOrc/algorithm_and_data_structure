/* Determine if a Sudoku is valid, according to: 3 rules to Sudoku:
1. Each row must have the numbers 1-9 occuring just once.
2. Each column must have the numbers 1-9 occuring just once.
3. The numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/

public class Solution {
    
    // 思路朴素，处理方式很巧妙
    // Ref: https://discuss.leetcode.com/topic/9748/shared-my-concise-java-code
    public boolean isValidSudoku(char[][] board)
    {
        // 用一个 9*9 的循环，一次性地解决所有9行、所有9列、所有9个小九宫格的检查
        for(int i = 0; i<9; i++)
        {
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            
            for (int j = 0; j < 9;j++)
            {
                // HashSet 的 add 方法如果返回false，则表示已经含有此元素
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                    
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                    
                // 非常巧妙！
                // i从0到8，通过 i/3 和 i%3 这两个方式的结合运用，很方便地得到了9个小九宫格的左上单元格的坐标：
                // [0,0], [0,1], [0,2]
                // [1,0], [1,1], [1,2]
                // [2,0], [2,1], [2,2]
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
    }
}

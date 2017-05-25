/* The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

Example
There exist two distinct solutions to the 4-queens puzzle:
[
  // Solution 1
  [".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  // Solution 2
  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
]

Challenge 
Can you do it without recursion? ——？？？？？？？ */

// DFS
// Ref: http://www.jiuzhang.com/solutions/n-queens/
class Solution {
    
    /* Get all distinct N-Queen solutions
     * @param n: The number of queens, also the width and height of the chessboard
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position */
    public ArrayList<ArrayList<String>> solveNQueens(int n) {
        
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        
        int[] queensCols = new int[n];
        for (int i = 0; i < n; i++) {
            queensCols[i] = -1; // 不能默认为0，因为0也是一个有效的col列序数
        }
        
        dfs(n, 0, queensCols, result);
        return result;
    }
    
    private void dfs(int n,
                     int numOfSettledQueens,
                     int[] queensCols,
                     ArrayList<ArrayList<String>> result) {
                         
        if (numOfSettledQueens == n) {
            ArrayList<String> oneSolution = parseASolution(queensCols);
            result.add(oneSolution);
            return;
        }
        
        // for every possible column of the new comer Queen
        for (int colIndex = 0; colIndex < n; colIndex++) {
            
            if (isAValidPosition(numOfSettledQueens, queensCols, colIndex)) {
                
                queensCols[numOfSettledQueens] = colIndex;
                dfs(n, numOfSettledQueens + 1, queensCols, result);
                queensCols[numOfSettledQueens] = -1; // 复位
            }
        }
    }
    
    private boolean isAValidPosition(int numOfSettledQueens,
                                     int[] queensCols,
                                     int colIndex) {
    
        int rowIndex = numOfSettledQueens; // 注意！这里是 index！所以不要再 +1 了！！
        
        for (int i = 0; i < numOfSettledQueens; i++) {
            int rowIndexOfFormerQueen = i;
            int colIndexOfFormerQueen = queensCols[i];
            
            // 同列：不可（同行不可能了）
            if (colIndex == colIndexOfFormerQueen) {
                return false;
            }
            
            // 左上到右下的斜线共线：不可
            if (rowIndex + colIndex == rowIndexOfFormerQueen + colIndexOfFormerQueen) {
                return false;
            }
            
            // 右上到左下的斜线共线：不可
            if (rowIndex - colIndex == rowIndexOfFormerQueen - colIndexOfFormerQueen) {
                return false;
            }
        }
        return true;
    }   
    
    private ArrayList<String> parseASolution(int[] queensCols) {
        
        ArrayList<String> oneSolution = new ArrayList<>();
        
        for (int i = 0; i < queensCols.length; i++) {
            int colIndexOfThisQueen = queensCols[i];
            
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queensCols.length; j++) {
                if (j == colIndexOfThisQueen) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            oneSolution.add(sb.toString());
        }
        return oneSolution;
    }
    
}

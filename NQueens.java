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
public class Solution {
  
  public List<List<Integer>> nqueens(int n) { // we have n > 0
    List<List<Integer>> result = new ArrayList<>();
    
    fillInQueens(n, 0, new ArrayList<Integer>(), result);
    return result;
  }
  
  private void fillInQueens(int n, int settledQueens, 
    ArrayList<Integer> queensCols, List<List<Integer>> result) {
    
    if (settledQueens == n) {
      result.add(new ArrayList<Integer>(queensCols));
      return;
    }
    
    for (int i = 0; i < n; i++) {
      
      if (isValidPos(i, queensCols)) {
        
        queensCols.add(i);
        fillInQueens(n, settledQueens + 1, queensCols, result);
        queensCols.remove(queensCols.size() - 1);
      }
    }
  }
  
  private boolean isValidPos(int newQueenCol, ArrayList<Integer> queensCols) {
    
    for (int i = 0; i < queensCols.size(); i++) {
      
      int formerQueenRow = i;
      int formerQueenCol = queensCols.get(i);
      
      int newQueenRow = queensCols.size();
      
      if (formerQueenCol == newQueenCol) {
        return false;
      }
      if (formerQueenCol + formerQueenRow == newQueenCol + newQueenRow) {
        return false;
      }
      if (formerQueenCol - formerQueenRow == newQueenCol - newQueenRow) {
        return false;
      }
    }
    return true;
  }
  
}

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

/* DFS
Time: O(n! * n)
    首先想到是 O(n^n), since we walk through n layers, and in each layer there are n slots,
    然后，剪枝（pruning）后是 O(n! * n)
    n! 是：第一层有n种可能，第二层对于第一层的每个选择，接下来有n-1种可能......所以一共是 n * (n-1) * (n-2) * ...
        其实，每一层缩减的速度要大大快于-1的速度，比如第一层确定以后，会直接导致第二层中不止一个，而是两到三个slot无法使用
    n 是：然后，每一种可能性，需要消耗 O(n) 时间的 check valid
Space: O(n)，因为有n层call stack，每一层是constant space
    然后每个辅助的 ArrayList<Integer> 需要 O(n)
    一共是 O(n) + O(n) = O(n) */

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

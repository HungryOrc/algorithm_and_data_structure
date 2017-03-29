/* According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules:

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 

1. Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.
此处列出的答案即满足 in-place 的要求

2. In this question, we represent the board using a 2D array. 
In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
How would you address these problems? 
思路：很巧妙！
Ref: https://discuss.leetcode.com/topic/26236/infinite-board-solution
For an infinite board, instead of a two-dimensional array of ones and zeros, 
I represent the board as a SET of LIVE cells' COORDINATES. 
然后就考察所有的live cells，以及这些cells的所有dead neighbors，看它们到了下一个step会是如何的状态 (live/dead) 
再往外圈的所有dead cells，是不可能在下一回合内就变live的，虽然它们确实有可能在再下一回合时变live，但这就不是这题要考虑的问题了 */

// 为了满足 follow up 第一条 in-place 的要求，把二维矩阵 board 里的每个元素都搞成两位数
// 否则不必这么绕。直接再搞一个二维矩阵，表示下一个状态就好
public class Solution {
    
    public void gameOfLife(int[][] board) {
        int LIVE = 1;
        int DEAD = 0;
        
        // 改造board里的元素。让它们都成为两位数。十位表示当前状态。个位表示下一步的状态
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] *= 10;
            }
        }
        
        int[] deltaX = new int[]{0, 0, 1, -1, 1, -1, 1, -1};
        int[] deltaY = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                
                // check each possible neighbor,
                // sum up the number of live cells among them
                int numOfLiveNeighbors = 0;
                for (int k = 0; k < 8; k++) {
                    int neighborX = i + deltaX[k];
                    int neighborY = j + deltaY[k];
                    
                    if (isValidCell(neighborX, neighborY, board.length, board[0].length)) {
                        if (board[neighborX][neighborY] / 10 == LIVE) {
                            numOfLiveNeighbors ++;
                        }
                    }
                }
                
                // 如果本cell目前是live的
                if (board[i][j] / 10 == LIVE) {
                    if (numOfLiveNeighbors < 2) {
                        board[i][j] += DEAD;
                    } else if (numOfLiveNeighbors <= 3) {
                        board[i][j] += LIVE;
                    } else { // > 3
                        board[i][j] += DEAD;
                    }
                } 
                // 如果本cell目前是dead的
                else {
                    if (numOfLiveNeighbors == 3) {
                        board[i][j] += LIVE;
                    } // else, do nothing. Namely leave it dead, and dead means 0
                }
            }
        }
        
        // 把matrix里每个元素的十位去掉，留下个位，即下一个状态的死/活情况
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] %= 10;
            }
        }
    }
    
    private boolean isValidCell(int x, int y, int m, int n) {
        return (x >= 0 && x < m && y >= 0 && y < n);
    }
}

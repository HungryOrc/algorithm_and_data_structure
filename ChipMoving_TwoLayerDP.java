/* You are given a grid filled with non-negative integer numbers, and a chip is placed on the top left cell. 
On each turn the chip can be moved either to the right neighboring cell or to the bottom neighboring cell. 
The cost of the move is equal to the number written on the resulting cell.
There is also an added cost if you change direction between two consecutive moves. 
In order to change the direction you'll need to pay an additional cost of 10.

What is the minimum amount of points needed to reach the right bottom corner of the grid?
Assume that you don't pay for standing on the upper left cell in the beginning, so the number on that cell is not used.
It is guaranteed that each of all possible paths will cost less than 10^7 points.
Constraints:
1 ≤ grid.length ≤ 10,
1 ≤ grid[0].length ≤ 10,
0 ≤ grid[i][j] ≤ 2000.

Example
grid = [[ 0,  0, 99, 99, 99],
        [99,  0,  0,  0, 99],
        [99, 99, 99,  0, 99],
        [99, 99, 99,  0, 99],
        [99, 99, 99,  0,  0]]
the output should be: chipMoving(grid) = 40.
You could avoid all the 99 spots but you would need to change direction 4 times. */

// 我的方法。代码较长，但思路很简明清晰：使用2个DP矩阵，
// 一个DP矩阵记录从grid左上角开始，经过若干步，最近的一步是从当前cell的左邻cell到达当前cell，这种情况下的最小cost（含当前cell的值）
// 一个DP矩阵记录从grid左上角开始，经过若干步，最近的一步是从当前cell的上邻cell到达当前cell，这种情况下的最小cost（含当前cell的值）
// 最后的结果就是这2个DP矩阵在grid的右下角处，哪个矩阵在那一个cell的值小，就取谁的值
public int chipMoving(int[][] grid) {

    int rows = grid.length;
    int cols = grid[0].length;
    
    int directChangeCost = 10;
    
    int[][] costToReachCurCell_FromAbove = new int[rows][cols];
    int[][] costToReachCurCell_FromLeft = new int[rows][cols];
    
    for (int col = 1; col < cols; col++) {
        costToReachCurCell_FromLeft[0][col] = costToReachCurCell_FromLeft[0][col - 1] + grid[0][col];
        // actually we will never use the following values
        costToReachCurCell_FromAbove[0][col] = -1;
    }
    for (int row = 1; row < rows; row++) {
        costToReachCurCell_FromAbove[row][0] = costToReachCurCell_FromAbove[row - 1][0] + grid[row][0];
        // actually we will never use the following values
        costToReachCurCell_FromLeft[row][0] = -1;
    }
    
    for (int col = 1; col < cols; col++) {
        costToReachCurCell_FromAbove[1][col] = costToReachCurCell_FromLeft[0][col] + directChangeCost + grid[1][col];
    }
    for (int row = 1; row < rows; row++) {
        costToReachCurCell_FromLeft[row][1] = costToReachCurCell_FromAbove[row][0] + directChangeCost + grid[row][1];
    }
    
    for (int col = 2; col < cols; col++) {
        costToReachCurCell_FromLeft[1][col] = grid[row][1] + 
                Math.min(costToReachCurCell_FromLeft[1][col - 1], 
                         costToReachCurCell_FromAbove[1][col - 1] + directChangeCost);
    }
    for (int row = 2; row < rows; row++) {
        costToReachCurCell_FromAbove[row][1] = grid[row][1] + 
                Math.min(costToReachCurCell_FromAbove[row - 1][1], 
                         costToReachCurCell_FromLeft[row - 1][1] + directChangeCost);
    }
    
    for (int row = 2; row < rows; row++) {
        for (int col = 2; col < cols; col++) {
            costToReachCurCell_FromAbove[row][col] = grid[row][col] + 
                    Math.min(costToReachCurCell_FromAbove[row - 1][col], 
                             costToReachCurCell_FromLeft[row - 1][col] + directChangeCost);
            costToReachCurCell_FromLeft[row][col] = grid[row][col] + 
                    Math.min(costToReachCurCell_FromLeft[row][col - 1], 
                             costToReachCurCell_FromAbove[row][col - 1] + directChangeCost);
        }
    }

    return Math.min(costToReachCurCell_FromAbove[rows - 1][cols - 1], 
                    costToReachCurCell_FromLeft[rows - 1][cols - 1]);   
}

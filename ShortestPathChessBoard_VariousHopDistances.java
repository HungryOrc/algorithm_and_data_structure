/* 从左上角到右下角，每一步走多远，取决于那个cell上的数字的大小。这些数字都是非负整数。
求最后到达棋盘的右下角最少需要多少步。如果无论如何也不能到达，则返回 -1。比如下面这个棋盘：
2 3 0 1
1 1 4 5
2 3 1 1
从[0, 0]开始，因为它上面的数字是2，所以第一步可以到达 [0, 2]，或者[2, 0]。然后这么一步一步走下去。


注意：
(1) 每一步都要考察，出界了没有
(2) 如果本cell的数字为0，则终结此分支！！！不然会永远停在本cell上死循环！！！ */

class Cell {
    int x, y;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
  
    public int findMinSteps(int[] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == 0) {
            return -1;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
      
        int curStep = 0;
        Queue<Cell> cellQueue = new LinkedList<Cell>();
        cellQueue.offer(new Cell(0, 0));
      
        while (!cellQueue.isEmpty()) {
            int curLayerSize = cellQueue.size();
          
            for (int i = 0; i < curLayerSize; i++) {
                Cell curCell = cellQueue.poll();
                int curX = curCell.x;
                int curY = curCell.y;
                if (curX == rows - 1 && curY == cols - 1) {
                    return curStep;
                }
                
                int curHopDistance = matrix[curX][curY];
                if (curHopDistance != 0) {
                    Cell cellRight = new Cell(curX, curY + curHopDistance);
                    if (inBound(cellRight, rows, cols)) {
                        cellQueue.offer(cellRight);
                    }
                    Cell cellDown = new Cell(curX + curHopDistance, curY);
                    if (inBound(cellDown, rows, cols)) {
                        cellQueue.offer(cellDown);
                    }
                }
                curStep ++;
            }
        }  
      
        return -1;
    }
    
    private boolean inBound(Cell cell, int rows, int cols) {
        return (cell.x >= 0 && cell.x < rows && cell.y >= 0 && cell.y < cols);
    }
    
}

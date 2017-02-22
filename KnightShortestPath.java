/* Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, 
find the shortest path to a destination position, return the length of the route. 
Return -1 if knight can not reached.

Notice
source and destination must be empty.
Knight can not enter the barrier.

Clarification
If the knight is at (x, y), he can get to the following positions in one step:
(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)

Example
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return 2

[[0,1,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return 6

[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return -1 */

/* Definition for a point.
 * public class Point {
 *     public int x, y;
 *     public Point() { x = 0; y = 0; }
 *     public Point(int a, int b) { x = a; y = b; }
 * } */
public class Solution {
    int[] deltaX = {1, 1, -1, -1, 2, 2, -2, -2};
    int[] deltaY = {2, -2, 2, -2, 1, -1, 1, -1};
    boolean AVAILABLE = false;
    boolean UNAVAILABLE = true;
    
    /* @param grid a chessboard included 0 (false) and 1 (true)
     * @param source, destination a point
     * @return the shortest path */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.offer(source);
        int steps = 0;
        
        while (!pointQueue.isEmpty()) {
            int sizeOfCurLayer = pointQueue.size();
            
            // 下一步
            for (int i = 1; i <= sizeOfCurLayer; i++) {
                Point curPoint = pointQueue.poll();
                
                if (curPoint.x == destination.x && curPoint.y == destination.y) {
                    return steps;
                }
                
                // 向周围八个方向各尝试着走一步
                for (int j = 0; j < 8; j++) {
                    int nextX = curPoint.x + deltaX[j];
                    int nextY = curPoint.y + deltaY[j];
                    Point nextStep = new Point(nextX, nextY);
                    
                    if (inBound(nextStep, rows, cols) && isAvailable(nextStep, grid)) {
                        pointQueue.offer(nextStep);
                        grid[nextStep.x][nextStep.y] = UNAVAILABLE;
                    }
                }
            }
            steps ++;
        }
        // if we reach here, then it means the destination will never be reachable
        return -1;
    }
    private boolean inBound(Point curPoint, int rows, int cols) {
        return (curPoint.x >= 0 && curPoint.x < rows && curPoint.y >= 0 && curPoint.y < cols);
    }
    private boolean isAvailable(Point curPoint, boolean[][] grid) {
        return grid[curPoint.x][curPoint.y] == AVAILABLE;
    }
    
}

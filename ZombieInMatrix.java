/* Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0 (the number zero, one, two).
Zombies can turn the nearest people(up/down/left/right) into zombies every day, but can not through wall. 
How long will it take to turn all people into zombies? Return -1 if can not turn all people into zombies.
每个Zombie每天把它四周的四个格子上都咬一遍，如果是人就变成Zombie。老Zombie就再也没用了。
然后第二天轮到新的Zombie再向它们的周围咬。
如果到了某一天，人的个数降到零，证明人死光了，return days。
如果Queue循环完了，人都还没死光，证明有的人被四面墙保护在中间了，这样的话永远无法咬到所有人。return -1.

Example
Given a matrix:
0 1 2 0 0
1 0 0 2 1
0 1 0 0 0
return 2 */

// BFS
// Ref: http://www.jiuzhang.com/solutions/zombie-in-matrix/
class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public int PEOPLE = 0;
    public int ZOMBIE = 1;
    public int WALL = 2;
    
    public int[] deltaX = {0, 0, 1, -1};
    public int[] deltaY = {1, -1, 0, 0};
    
    /* @param grid  a 2D integer grid
     * @return an integer */
    public int zombie(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        
        int people = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == PEOPLE) {
                    people ++;
                } else if (grid[i][j] == ZOMBIE) {
                    queue.offer(new Coordinate(i, j));
                }
            }
        }
        
        // corner case
        if (people == 0) {
            return 0;
        }
        
        // BFS
        int days = 0;
        while (!queue.isEmpty()) {
            days ++;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Coordinate zb = queue.poll();
                
                for (int direction = 0; direction < 4; direction++) {
                    Coordinate adjacent = new Coordinate(zb.x + deltaX[direction], zb.y + deltaY[direction]);
                    
                    if (inBound(adjacent, n, m) && isPeople(adjacent, grid)) {
                        people --;
                        if (people == 0) {
                            return days;
                        }
                        
                        grid[adjacent.x][adjacent.y] = ZOMBIE;
                        // a new zombie has just been converted, put it into the queue now!
                        queue.offer(adjacent);
                    }
                }
            }
        }
        return -1;
    }
    private boolean inBound(Coordinate coord, int n, int m) {
        return (coord.x >= 0 && coord.x < n && coord.y >= 0 && coord.y < m);
    }
    private boolean isPeople(Coordinate coord, int[][] grid) {
        return grid[coord.x][coord.y] == PEOPLE;
    }
    
}

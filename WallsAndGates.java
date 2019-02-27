/* You are given a m x n 2D grid initialized with these three possible values.

* -1: a wall or an obstacle.
* 0: a gate.
* INF: Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that 
the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. 
If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
  
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

别人的方法。挺巧妙的：Java BFS Solution-O(mn) Time
Ref: https://leetcode.com/problems/walls-and-gates/
Push all gates into queue first. Then for each gate update its neighbor cells and push them to the queue.
Repeating above steps until there is nothing left in the queue. */

public class Solution 
{
    public void wallsAndGates(int[][] rooms) 
    {
        if (rooms.length == 0 || rooms[0].length == 0) 
            return;
      
        // 把所有的gates放到queue里面去
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) 
                    queue.add(new int[]{i, j});
            }
        }
        
        // 关键！！！
        // 当所有能够reach到的cell（即element）的值都不是 INF 的时候，queue就空了，我们的循环也可结束了
        // 那些无法被reach到的cell 就是不可达到的密闭房间
        while (!queue.isEmpty()) 
        {
            int[] top = queue.remove();
            int row = top[0], col = top[1];
          
            // 只改写那些当前值为 INF 的 cell
            // 一旦一个cell的值不再是INF了，则不会再被改写
            if (row >= 1 && rooms[row - 1][col] == Integer.MAX_VALUE) 
            {
                rooms[row - 1][col] = rooms[row][col] + 1; // 下一个cell的距离是当前cell的距离 +1
                queue.add(new int[]{row - 1, col});
            }
            if (row <= rooms.length - 2 && rooms[row + 1][col] == Integer.MAX_VALUE) 
            {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            if (col >= 1 && rooms[row][col - 1] == Integer.MAX_VALUE) 
            {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            if (col <= rooms[0].length - 2 && rooms[row][col + 1] == Integer.MAX_VALUE) 
            {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
        }
    }
}

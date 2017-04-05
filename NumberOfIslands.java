/* Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.
Find the number of islands.

Example
Given graph:
[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3. */

// 九章答案：http://www.jiuzhang.com/solutions/number-of-islands/

// 方法1: BFS
class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
      
        int n = grid.length;
        int m = grid[0].length;
        int islands = 0;
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == true) {
                    markAdjacentIslandCellsToFalse(grid, i, j);
                    islands ++;
                }
            }
        }
        return islands;
    }
  
    private void markAdjacentIslandCellsToFalse(boolean[][] grid, int x, int y) {
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(x, y));
        grid[x][y] = false;
      
        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
          
            for (int direction = 0; direction < 4; direction++) {
                Coordinate adjacent = new Coordinate(coor.x + deltaX[direction], coor.y + deltaY[direction]);
              
                if (!inBound(adjacent, grid.length, grid[0].length)) {
                    continue;
                }
                if (grid[adjacent.x][adjacent.y] == true) {
                    grid[adjacent.x][adjacent.y] = false;
                    queue.offer(adjacent);
                }
            }
        }
    }
  
    private boolean inBound(Coordinate coor, int n, int m) {
        return (coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m);
    }
}


// 方法2: DFS
public class Solution {
    private int m, n;
    
    public int numIslands(boolean[][] grid) {
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;
        if (n == 0) return 0;
        
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == false) { 
                    continue;
                } else { // grid[i][j] == true
                    result ++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }
    
    public void dfs(boolean[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) 
            return;
        
        if (grid[i][j] == true) {
            grid[i][j] = false;
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }
    }
}


// 方法3: Union Find 还没看？？？
class UnionFind {
    private int[] father = null;
    private int count;

    private int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    public UnionFind(int n) {
        // initialize your data structure here.
        father = new int[n];
        for (int i = 0; i < n; ++i) {
            father[i] = i;
        }
    }
    public void connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            count --;
        }
    }
    public int query() {
        // Write your code here
        return count;
    }
    public void set_count(int total) {
        count = total;
    }
}

public class Solution {
    /* @param grid a boolean 2D matrix
     * @return an integer */
    public int numIslands(boolean[][] grid) {
        int count = 0;
        int n = grid.length;
        if (n == 0)
            return 0;
        int m = grid[0].length;
        if (m == 0)
            return 0;
        UnionFind union_find = new UnionFind(n * m);
        
        int total = 0;
        for(int i = 0;i < grid.length; ++i)
            for(int j = 0;j < grid[0].length; ++j)
            if (grid[i][j])
                total ++;
    
        union_find.set_count(total);
        for(int i = 0;i < grid.length; ++i)
            for(int j = 0;j < grid[0].length; ++j)
            if (grid[i][j]) {
                if (i > 0 && grid[i - 1][j]) {
                    union_find.connect(i * m + j, (i - 1) * m + j);
                }
                if (i <  n - 1 && grid[i + 1][j]) {
                    union_find.connect(i * m + j, (i + 1) * m + j);
                }
                if (j > 0 && grid[i][j - 1]) {
                    union_find.connect(i * m + j, i * m + j - 1);
                }
                if (j < m - 1 && grid[i][j + 1]) {
                    union_find.connect(i * m + j, i * m + j + 1);
                }
            }
        return union_find.query();
    }
}

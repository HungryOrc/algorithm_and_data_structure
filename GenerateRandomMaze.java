/* Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. 
For each pair of cells on the corridor, there must exist one and only one path between them. 
(Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). 
The wall is denoted by 1 in the matrix and corridor is denoted by 0.

Assumptions:
N = 2K + 1 and K >= 0
the top left corner must be corridor
there should be as many corridor cells as possible
for each pair of cells on the corridor, there must exist one and only one path between them

Examples:
If N = 5, one possible maze generated is:

        0  0  0  1  0
        1  1  0  1  0
        0  1  0  0  0
        0  1  1  1  0
        0  0  0  0  0   */
        
/* 思路：Laioffer的方法。从某个点出发，在某个方向上是否可以打出路来，充分必要条件是：向这个方向走两格，看终点是否为墙，
如果是墙，则可以打过去，打过去的方法是把这两格都改为走廊。 */

public class Solution {
  
  public int[][] maze(int n) {
    int[][] maze = new int[n][n];
    
    // initialize the matrix, only (0, 0) is a corridor,
    // all other cells are walls at the beginning.
    // later we are trying to break the walls to form corridors
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          maze[i][j] = 0; // 0 means corridor
        } else {
          maze[i][j] = 1; // 1 means wall
        }
      }
    }
    
    generate(maze, 0, 0);
    
    return maze;
  }
  
  private void generate(int[][] maze, int x, int y) {
    // get a random shuffle of all the possible directions,
    // and follow the shuffled order to do DFS & backtrack
    Dir[] dirs = Dir.values();
    shuffle(dirs);
    
    for (Dir dir : dirs) {
      // advance by 2 cells
      int nextX = dir.moveX(x, 2);
      int nextY = dir.moveY(y, 2);
      
      if (isValidWall(maze, nextX, nextY)) {
        // only if the cell is a wall, namely we have not visited before,
        // we can break the walls through to change them to corridors
        maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
        maze[nextX][nextY] = 0;
        
        generate(maze, nextX, nextY);
      }
    }
  }
  
  // get a random order of the directions
  private void shuffle(Dir[] dirs) {
    for (int i = 0; i < dirs.length; i++) {
      int index = (int)(Math.random() * (dirs.length - i));
      Dir tmp = dirs[i];
      dirs[i] = dirs[i + index];
      dirs[i + index] = tmp;
    }
  }
  
  // check if the position (x, y) is within the maze and is a wall
  private boolean isValidWall(int[][] maze, int x, int y) {
    return x >= 0 && x < maze.length &&
      y >= 0 && y < maze[0].length &&
      maze[x][y] == 1;
  }
  
  // this is an example of using enum in java
  // basically enum is good for representing a set of predefined constants
  enum Dir {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, -1), WEST(0, 1);
    
    int deltaX;
    int deltaY;
    
    Dir(int deltaX, int deltaY) {
      this.deltaX = deltaX;
      this.deltaY = deltaY;
    }
    
    // move certain times by delta x
    public int moveX(int x, int times) {
      return x + times * deltaX;
    }
    
    // move certain times by delta y
    public int moveY(int y, int times) {
      return y + times * deltaY;
    }
  }
}

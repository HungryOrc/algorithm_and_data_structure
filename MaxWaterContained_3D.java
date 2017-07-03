/* Given a non-negative integer 2D array representing the heights of bars in a matrix. 
Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. 
The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. 
Each bar has 4 neighboring bars to the left, right, up and down side.

Assumptions: 
The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.

Examples:
{ { 2, 3, 4, 2 },
  { 3, 1, 2, 3 },
  { 4, 3, 5, 4 } }
the amount of water can be trapped is 3, 
at position (1, 1) there is 2 units of water trapped,
at position (1, 2) there is 1 unit of water trapped.   */


/* 思路：用 min heap 做，heap里的元素是matrix上的nodes，heap的排序规则是nodes的heights从小到大排。
先把matrix四周四个边上的nodes都放到heap里去，这些nodes每个node上的“水位”认为就是它们各自的 height。
注意 ！！！ 边界上最低的node，就代表了整个matrix上所有的点的最低的水位 ！！！

然后从heap里不断pop nodes出来，每次pop出来的都是当前height最小的node。
然后拓展这个被pop出来的node的neighbors。
注意 ！！！ 每个neighbor的水位的最低可能值，就是当前node的水位 ！！！ 

这整个过程就是一个从四周到中心的   逐   步   推   高   的过程 ！！！   */


// Laioffer 的方法。看起来很长。其实核心只有水位变化的那一小段。其它的都很简单

// helper class
class Pair implements Comparable<Pair> {
  int x, y;
  int height;
  
  public Pair(int x, int y, int height) {
    this.x = x;
    this.y = y;
    this.height = height;
  }
  
  @Override
  public int compareTo(Pair another) {
    if (this.height == another.height) {
      return 0;
    }
    return this.height < another.height ? -1 : 1;
  }
}

public class Solution {
  
  public int maxTrapped(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    if (rows < 3 || cols < 3) {
      return 0;
    }
    
    /* the min heap maintains all the BORDER CELLS of the "CLOSED AREA",
     then we always find the one with lowest height to see if any of its neighbors
     can trap any water */
    PriorityQueue<Pair> minHeap = new PriorityQueue<>();
    
    boolean[][] visited = new boolean[rows][cols];
    
    // put all the border cells of the matrix at the beginning
    processBorder(matrix, visited, minHeap, rows, cols);
    
    int result = 0;
    
    while (!minHeap.isEmpty()) {
      Pair cur = minHeap.poll();
      
      // get all possible neighbor cells
      List<Pair> neighbors = allNeighbors(cur, matrix, visited);
      
      for (Pair nei : neighbors) {
        if (visited[nei.x][nei.y]) {
          continue;
        }
        
        // adjust the neighbor cell's height to the current water level.
        result += Math.max(cur.height - nei.height, 0);
        nei.height = Math.max(cur.height, nei.height);
        
        visited[nei.x][nei.y] = true;
        minHeap.offer(nei);
      }
    }
    return result;
  }
  
  // the border cells are the starting points of the whole BFS process
  private void processBorder(int[][] matrix, boolean[][] visited, 
    PriorityQueue<Pair> minHeap, int rows, int cols) {
    
      for (int j = 0; j < cols; j++) {
        minHeap.offer(new Pair(0, j, matrix[0][j]));
        minHeap.offer(new Pair(rows - 1, j, matrix[rows - 1][j]));
        visited[0][j] = true;
        visited[rows - 1][j] = true;
      }
      for (int i = 1; i < rows - 1; i++) {
        minHeap.offer(new Pair(i, 0, matrix[i][0]));
        minHeap.offer(new Pair(i, cols - 1, matrix[i][cols - 1]));
        visited[i][0] = true;
        visited[i][cols - 1] = true;
      }
  }
  
  private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited) {
    List<Pair> neis = new ArrayList<>();
    
    if (cur.x + 1 < matrix.length) {
      neis.add(new Pair(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
    }
    if (cur.x - 1 >= 0) {
      neis.add(new Pair(cur.x - 1, cur.y, matrix[cur.x - 1][cur.y]));
    }
    if (cur.y + 1 < matrix[0].length) {
      neis.add(new Pair(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
    }
    if (cur.y - 1 >= 0) {
      neis.add(new Pair(cur.x, cur.y - 1, matrix[cur.x][cur.y - 1]));
    }
    return neis;
  }
}

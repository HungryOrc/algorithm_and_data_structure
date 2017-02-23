/* Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), 
find a place to build a post office so that the sum of the distance from the post office to all the houses is smallest.
Return the smallest sum of distance. Return -1 if it is not possible.

Notice
You cannot pass through wall and house, but can pass through empty.
You only build post office on an empty.
Challenge 
Solve this problem within O(n^3) time.

Example
Given a grid:
0 1 0 0 0
1 0 0 2 1
0 1 0 0 0
return 8, You can build at (1,1). (Placing a post office at (1,1), the distance that post office to all the house sum is smallest.) */

class Coord {
    public int x;
    public int y;
    public Coord(int xSet, int ySet) {
        this.x = xSet;
        this.y = ySet;
    }
}

// 注意！！到达一个HOUSE以后，不可以再以此HOUSE为出发点向四周走！！
// HOUSE 就像一个 特殊的 WALL
public class Solution {
    int WALL = 2;
    int HOUSE = 1;
    int EMPTY = 0;
    int[] deltaX = {0, 0, 1, -1};
    int[] deltaY = {1, -1, 0, 0};
    
    /* @param grid a 2D grid
     * @return an integer */
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int numOfHouses = 0;
        ArrayList<Coord> emptyCoords = new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == HOUSE) {
                    numOfHouses ++;
                } else if (grid[i][j] == EMPTY) {
                    emptyCoords.add(new Coord(i, j));
                }
            }
        }
        if (emptyCoords.size() == 0) { // initially no any empty cell
            return -1;
        }
        
        // final result
        int minSumOfDists = Integer.MAX_VALUE; 

        // for each empty cell in the grid, we set it as the post office, in turn
        for (int i = 0; i < emptyCoords.size(); i++) {
            // the starting cell for this loop, namely, the chosen cell of post office for this loop
            Coord curPostOffice = emptyCoords.get(i);
            
            int curMinSumOfDists = 0;
            int curStep = 0;
            
            int connectedHouses = 0;
            boolean[][] visited = new boolean[rows][cols]; // default: false
            
            Queue<Coord> unvisitedCoords = new LinkedList<>();
            unvisitedCoords.offer(curPostOffice);
            
            while (!unvisitedCoords.isEmpty()) {
                
                // for the next step
                int sizeOfCurLayer = unvisitedCoords.size();
                for (int j = 0; j < sizeOfCurLayer; j++) {
                    
                    Coord curCoord = unvisitedCoords.poll();
                
                    // if we step into a wall, or a visited cell, then do nothing
                    if (grid[curCoord.x][curCoord.y] != WALL && visited[curCoord.x][curCoord.y] == false) {
                        
                        // if we step into a house (unvisited house)
                        if (grid[curCoord.x][curCoord.y] == HOUSE) {
                            curMinSumOfDists += curStep;
                            connectedHouses ++;
                        }
                        
                        // if we step into an empty cell (unvisited empty cell)
                        if (grid[curCoord.x][curCoord.y] == EMPTY) {
                            // we go on to 4 directions
                            for (int k = 0; k < 4; k++) {
                                Coord nextStep = new Coord(curCoord.x + deltaX[k], curCoord.y + deltaY[k]);
                                
                                // add valid next-step-cells into the queue
                                if (inBound(nextStep, rows, cols) && visited[nextStep.x][nextStep.y] == false) {
                                    unvisitedCoords.offer(nextStep);
                                }
                            }
                        }
                        
                        // the cell we stepped into, either a house or an empty cell, should be marked as visited
                        visited[curCoord.x][curCoord.y] = true;
                    }  
                }
                curStep ++;
            }
            
            if (connectedHouses == numOfHouses) { // if all houses have been connected
                minSumOfDists = Math.min(minSumOfDists, curMinSumOfDists);
            }
        }
        
        // if we cannot reach every house, no matter which empty cell we choose to place the post office
        if (minSumOfDists == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minSumOfDists;
        }
    }
    
    private boolean inBound(Coord coord, int rows, int cols) {
        return (coord.x >= 0 && coord.x < rows && coord.y >= 0 && coord.y < cols);
    }
    
}

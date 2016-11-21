/* You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). 
The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
Determine the perimeter of the island.

Example:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]
Answer: 16
*/

public class Solution {
    
    // 我的比较朴素的 O(n*m) 算法，逐个格子查看
    // 但既然题中对于岛的形状有一些限制，那么应该是有不需要遍历所有格子的更简便方法，我暂时没想到
    public int islandPerimeter(int[][] grid) {
        
        if (grid == null)
            return 0;
        
        int perimeter = 0;
        int height = grid.length;
        int width = grid[0].length;
        
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (i == 0 && grid[i][j] == 1)
                    perimeter ++;
                else if (grid[i][j] == 1 && grid[i-1][j] == 0)
                    perimeter ++;
                    
                if (i == height-1 && grid[i][j] == 1)
                    perimeter ++;
                else if (grid[i][j] == 1 && grid[i+1][j] == 0)
                    perimeter ++;
                
                if (j == 0 && grid[i][j] == 1)
                    perimeter ++;
                else if (grid[i][j] == 1 && grid[i][j-1] == 0)
                    perimeter ++;
                    
                if (j == width-1 && grid[i][j] == 1)
                    perimeter ++;
                else if (grid[i][j] == 1 && grid[i][j+1] == 0)
                    perimeter ++;
            }
        }
        return perimeter;
    }
}

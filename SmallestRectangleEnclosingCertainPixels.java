/* An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. 
Given the location (x, y) of one of the black pixels, 
return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example
For example, given the following image:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6. */

// 方法1：二分法。时间 m * log(n)
// Ref: http://www.jiuzhang.com/solutions/smallest-rectangle-enclosing-black-pixels/
public class Solution {
    
    /* @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer */
    public int minArea(char[][] image, int x, int y) {
        
        int m = image.length;
        if (m == 0)
            return 0;
        int n = image[0].length;
        if (n == 0)
            return 0;

        int start = y;
        int end = n - 1;
        int mid;
        while (start < end) {
            mid = start + (end - start) / 2 + 1;
            if (checkColumn(image, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        int right = start;
        
        start = 0;
        end = y;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (checkColumn(image, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        int left = start;
        
        start = x;
        end = m - 1;
        while (start < end) {
            mid = start + (end - start) / 2 + 1;
            if (checkRow(image, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        int down = start;
        
        start = 0;
        end = x;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (checkRow(image, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        int up = start;
        
        return (right - left + 1) * (down - up + 1);
    }
    
    private boolean checkColumn(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkRow(char[][] image, int row) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[row][j] == '1') {
                return true;
            }
        }
        return false;
    }
}


// 方法2：BFS。在这一题里，BFS是慢的，时间 n平方
class Coord {
    public int x, y;
    public Coord (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    int[] deltaX = {1, -1, 0, 0};
    int[] deltaY = {0, 0, 1, -1};
    int minX, maxX;
    int minY, maxY;
    
    /* @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        
        int rows = image.length;
        int cols = image[0].length;
        minX = rows - 1;
        maxX = 0;
        minY = cols - 1;
        maxY = 0;
        
        boolean[][] visited = new boolean[rows][cols]; // default false
        Queue<Coord> coordQueue = new LinkedList<>();
        coordQueue.offer(new Coord(x, y));
        
        while (!coordQueue.isEmpty()) {
            Coord curCoord = coordQueue.poll();
            visited[curCoord.x][curCoord.y] = true;
            
            minX = Math.min(minX, curCoord.x);
            maxX = Math.max(maxX, curCoord.x);
            minY = Math.min(minY, curCoord.y);
            maxY = Math.max(maxY, curCoord.y);
            
            for (int i = 0; i < 4; i++) {
                int nextX = curCoord.x + deltaX[i];
                int nextY = curCoord.y + deltaY[i];
                Coord nextCoord = new Coord(nextX, nextY);
                if (inBound(nextCoord, rows, cols) && !visited[nextX][nextY] && image[nextX][nextY] == '1') {
                    coordQueue.offer(nextCoord);
                    visited[nextX][nextY] = true;
                }
            }
        }
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
    
    private boolean inBound(Coord coord, int rows, int cols) {
        return (coord.x >= 0 && coord.x < rows && coord.y >= 0 && coord.y < cols);
    }
}

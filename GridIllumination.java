/* N乘以N的矩阵。左下角是(1, 1)，右上角是(N, N)。
list lamps存了L个灯的坐标。每个灯的照明范围与国际象棋的皇后相同。
一个狗头人小队要随机查看一些cell的照明情况。查看时，要关闭本cell上的灯以及本cell周围的8个cell上的灯（if any），
然后看本cell是否还能被任何别的lamp照到。查完此cell后，再把所有暂时被关掉的灯打开。
list queries 给出我们要查看的M个cells的坐标。
几个参数的可能取值范围如下：
N <= 10^9 ---- 注意！这个范围超过了 int！要用 long ！！
L <= 10^5
M <= 10^5
返回一个ArrayList<String>，逐个列出对queries里的各个cell的检查结果，
"LIGHT"表示仍被照明，"DARK"表示无照明。 */

/* 思路：
Ref: http://www.1point3acres.com/bbs/thread-201983-1-1.html
用4个hashmap存每一个row、col、及2条对角线上所有灯的个数。
这4个map的key分别存：x，y，x+y，x-y；value即相关条件下的灯的个数。
用一个hashset存所有灯的位置。
对每一个query遍历9个格子，更新hashmap的记录，最后看看query所在的row col 两对角线在hashmap里的count是否大于0。 

注意！！！关于 HashMap 的复制！！！
无论用 new HashMap<>(oldHashMap) 的方法，还是 oldHashMap.clone() 的方法，得到的都是 shallow copy ！！！
Map里的 keys 和 values 都没有被真正的复制！！！ 
所以我们在这里还是老老实实一个一个灯地开，然后原路返回一个一个灯地关，不试图复制关于灯的 4个Map 了 */

import java.util.*;

class Cell {
    long x, y;
    public Cell(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

public class GridIllumination {

    public ArrayList<String> checkIllumination(long N, ArrayList<Cell> lamps, ArrayList<Cell> queries) {
        ArrayList<String> result = new ArrayList<>();
        
        // <x coordinate, lamp count>
        HashMap<long, int> lampCountsInRows = new HashMap<>();
        // <y coordinate, lamp count>
        HashMap<long, int> lampCountsInCols = new HashMap<>();
        // <diagonal x + y, lamp count> 
        // 对角线是左上到右下，因为本题原点在左下角，所以对角线的表示是 x + y = 一定值
        HashMap<long, int> lampCountsInDiags = new HashMap<>();
        // <anti-diagonal x - y, lamp count> 
        // 反对角线是右上到左下，因为本题原点在左下角，所以反对角线的表示是 x - y = 一定值
        HashMap<long, int> lampCountsInAntidiags = new HashMap<>();
      
        HashSet<Cell> allLamps = new HashSet<>();
        for (Cell lamp : lamps) {
            // 把所有灯的坐标从list里放到set里，更方便查找
            allLamps.add(lamp);
          
            // 把所有灯的照明区域加到4个map里去
            lampCountsInRows.put(lamp.x, getOrDefault(lamp.x, 0) + 1);
            lampCountsInCols.put(lamp.y, getOrDefault(lamp.y, 0) + 1);
            lampCountsInDiags.put(lamp.x + lamp.y, getOrDefault(lamp.x + lamp.y, 0) + 1);
            lampCountsInAntidiags.put(lamp.x - lamp.y, getOrDefault(lamp.x - lamp.y, 0) + 1);
        }
      
        // for each check point in the check list
        for (Cell checkPoint : queries) {             
            ArrayList<Cell> relevantCells = getRelevantCells(checkPoint, N);
          
            HashMap<long, int> copyMapRows = new HashMap<>(lampCountsInRows);
            HashMap<long, int> copyMapCols = new HashMap<>(lampCountsInRows);
            HashMap<long, int> copyMapDiags = new HashMap<>(lampCountsInDiags);
            HashMap<long, int> copyMapAntidiags = new HashMap<>(lampCountsInAntidiags);
            
            for (Cell cell : relevantCells) {
                // if any relevant cell has a lamp in it
                // remove its illumination areas (atcually illumination counts) from the grid
                if (allLamps.contains(cell)) {
                    copyMapRows.put(cell.x, copyMapRows.get(cell.x) - 1);
                    copyMapCols.put(cell.y, copyMapCols.get(cell.y) - 1);
                    copyMapDiags.put(cell.x + cell.y, copyMapDiags.get(cell.x + cell.y) - 1);
                    copyMapAntidiags.put(cell.x - cell.y, copyMapAntidiags.get(cell.x - cell.y) - 1);
                }
            }
        
            if (copyMapRows.get(checkPoint.x) == 0 && copyMapCols.get(checkPoint.y) &&
                copyMapDiags.get(checkPoint.x + checkPoint.y) == 0 &&
                copyMapAntidiags.get(checkPoint.x - checkPoint.y) == 0) {
                result.add("DARK");
            } else {
                result.add("LIGHT");
            }
        }
    }
    
    private ArrayList<Cell> getRelevantCells(Cell cell, long N) {
        ArrayList<Cell> result = new ArrayList<>();
        result.add(cell);
    
        int[] deltaX = new int[] {1, -1, 0, 0, 1, 1, -1, -1};
        int[] deltaY = new int[] {0, 0, 1, -1, 1, -1, 1, -1};
        for (int i = 0; i < 8; i++) {
            Cell neighbor = new Cell(cell.x + deltaX[i], cell.y + deltaY[i]);
            if (isInTheGrid(neighbor, N)) {
                result.add(neighbor);
            }
        }
        return result;
    }
  
    private boolean isInTheGrid(Cell cell, long N) {
        if (cell.x < 1 || cell.x > N || cell.y < 1 || cell.y > N) {
            return false;
        } else {
            return true;
        }
    }
  
}

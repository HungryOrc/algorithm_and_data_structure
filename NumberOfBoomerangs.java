/* Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that 
the distance between i and j equals the distance between i and k (the order of the tuple matters).
Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all 
in the range [-10000, 10000] (inclusive).

Example:
Input: [[0,0],[1,0],[2,0]]
Output: 2
Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
所以，这里要的是排列，不是组合。因为 ijk 与 ikj 是两个不同的答案
*/

public class Solution {
    
    public int numberOfBoomerangs(int[][] points) {
        
        int numOfBoomerangs = 0;
        int numOfPoints = points.length;
        int[][] dists = new int[numOfPoints][numOfPoints];
        
        for (int i = 0; i < numOfPoints-1; i++)
        {
            dists[i][i] = 0;
            for (int j = i+1; j < numOfPoints; j++)
            {
                if (i != j)
                {
                    dists[i][j] = (points[i][0] - points[j][0])*(points[i][0] - points[j][0]) + 
                        (points[i][1] - points[j][1])*(points[i][1] - points[j][1]);
                    dists[j][i] = dists[i][j];
                }
            }
        }
        
        for (int i = 0; i < numOfPoints; i++)
        {
            HashMap<Integer, Integer> occurredDists = new HashMap<>();
            for (int j = 0; j < numOfPoints; j++)
            {
                if (i != j)
                    occurredDists.put(dists[i][j], occurredDists.getOrDefault(dists[i][j], 0) + 1);
            }
            
            // ！注意！HashMap.values() returns a "Collection view" of the values contained in this map ！！
            // 可以用 enhanced for loop 直接遍历 Collection 里的元素 ！！不用先把这个Collection转化为Array
            for (int count : occurredDists.values())
            {
                // 排列，在 count 个数里取 2 个数的排列的种类数
                int numOfPermutationsForThisCount = count * (count-1);
                numOfBoomerangs += numOfPermutationsForThisCount;
            }
        }
        return numOfBoomerangs;
    }
    
    
}

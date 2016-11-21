/* Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class Solution {
    
    public List<List<Integer>> generate(int numRows) {
        
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // 第一层算作第1层，则第n层有n个node
        int curLevel = 1;
        for (int i = 0; i < curLevel && curLevel <= numRows; i++)
        {
            ArrayList<Integer> curLevelNodes = new ArrayList<>();
            for (int j = 0; j <= i; j++)
            {
                if (j == 0 || j == i)
                    curLevelNodes.add(1);
                else
                    // 关键在这一句：
                    // 每一个元素其实等于：上一行中与它同index的数，以及index-1的数，之和
                    curLevelNodes.add(result.get(curLevel-2).get(j-1) + result.get(curLevel-2).get(j));
            }
            result.add(curLevelNodes);
            curLevel++;
        }
        return result;
    }
}

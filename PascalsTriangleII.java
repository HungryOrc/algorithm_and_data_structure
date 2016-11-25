/* Given an index k, return the kth row of the Pascal's triangle.
For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space? */

// 要求的是 O(k) space，不是 O(k) time，所以不用太方
public class Solution {
    
    public List<Integer> getRow(int rowIndex) {
    
        ArrayList<Integer> result = new ArrayList<>();
        
        if (rowIndex < 0)
            return result;
        
        result.add(1);
        if (rowIndex == 0)
            return result;
            
        result.add(1);
        if (rowIndex == 1)
            return result;
        
        for (int i = 2; i <= rowIndex; i++)
        {
            // 窍门！！从右边往左边算，这样不会出现搅浑上一行与这一行的问题
            for (int j = i-1; j >= 1; j--)
                result.set(j, result.get(j-1) + result.get(j));
                
            result.add(1); // 在最右边加一个1
        }
        return result;
    }
}

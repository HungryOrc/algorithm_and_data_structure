/* Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Different from the previous question where weight is increasing from root to leaf, 
now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, 
and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17) 

 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * 
 * public interface NestedInteger 
 * {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a NestedInteger to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * } */

public class Solution 
{
    // 我的一种朴素思路
    // 第一层的weight设为-1，然后往下深入的逐层的weight为-2，-3，-4...... 最后所有weight + (层数+1)
    public int depthSumInverse(List<NestedInteger> nestedList) 
    {
        ArrayList<NestedInteger> niAL = new ArrayList<NestedInteger>();
        for (NestedInteger ni : nestedList)
            niAL.add(ni);
        
        ArrayList<Integer[]> valueAndWeight_AL = new ArrayList<>(); 
        
        int curWeight = -1;
        while (!niAL.isEmpty())
        {
            int remainingNumberOfNIsInThisLevel = niAL.size();
            while (remainingNumberOfNIsInThisLevel > 0)
            {
                NestedInteger curNI = niAL.remove(0);
                
                // if the current NestedInteger is a single Integer
                if (curNI.isInteger())
                {
                    int curValue = curNI.getInteger();
                    Integer[] valueAndWeightPair = new Integer[2];
                    valueAndWeightPair[0] = curValue;
                    valueAndWeightPair[1] = curWeight;
                    valueAndWeight_AL.add(valueAndWeightPair);
                }
                
                // if the current NestedInteger is a list (of Integers or lists or Integers and lists)
                else
                    niAL.addAll(curNI.getList());
                    
                remainingNumberOfNIsInThisLevel --;
            }
            curWeight --;
        }
        
        curWeight ++; // 最后一次loop，curWeight还减了1，最后减的这一次1是不应该减的
        int allAddWeight = curWeight * (-1) + 1; // 所有的数的weight都要加上这个值
        int result = 0;
        for (Integer[] pair : valueAndWeight_AL)
            result += pair[0] * (pair[1]+allAddWeight);
        return result;
    }
    
}

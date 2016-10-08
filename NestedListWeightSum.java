/* Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Example: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)   */

/*
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

public class Solution
{    
    // 方法：Depth-first Search (DFS)
    // Reference: https://leetcode.com/articles/nested-list-weight-sum/
    // Because the input is nested, it is natural to think about the problem in a recursive way. 
    // We go through the list of nested integers one by one, keeping track of the current depth d. 
    // If a nested integer is an integer nn, we calculate its sum as n×d. 
    // If the nested integer is a list, we calculate the sum of this list recursively using the same process but with depth d+1.
    //
    // The algorithm takes O(N) time, where N is the total number of nested elements in the input list. 
    // For example, the list [ [[[[1]]]], 2 ] contains 4 nested lists and 2 nested integers (1 and 2), so N=6.
    // In terms of space, at most O(D) recursive calls are placed on the stack, where D is the maximum level of nesting in the input. 
    // For example, D=2 for the input [[1,1],2,[1,1]], and D=3 for the input [1,[4,[6]]].
    //
    public int depthSum(List<NestedInteger> nestedList)
    {
        return manageOneNestedList(nestedList, 1);
    }
    
    public int manageOneNestedList(List<NestedInteger> curNestedList, int curDepth)
    {
        int partialSum = 0;
        
        for (int i = 0; i < curNestedList.size(); i++)
        {
            if (curNestedList.get(i).isInteger())
                partialSum += curNestedList.get(i).getInteger() * curDepth;

            else
                partialSum += manageOneNestedList(curNestedList.get(i).getList(), curDepth + 1);
        }
        return partialSum;
    }
    
    
    // 稍作简化的 manageOneNestedList 方法：用 (Type element : List) 的语法来遍历 nestedList
    // 运算速度并未提高，只是表达简洁了一点点
    public int manageOneNestedList(List<NestedInteger> curNestedList, int curDepth)
    {
        int partialSum = 0;
        
        for (NestedInteger curNestedSubList : curNestedList)
        {
            if (curNestedSubList.isInteger())
                partialSum += curNestedSubList.getInteger() * curDepth;

            else
                partialSum += manageOneNestedList(curNestedSubList.getList(), curDepth + 1);
        }
        return partialSum;
    }
    
    
}

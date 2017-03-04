/* Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Notice: You don't need to implement the remove method.

Example
Given the list [[1,1],2,[1,1]], By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be: [1,1,2,1,1].
Given the list [1,[4,[6]]], By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be: [1,4,6].

 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation:
 
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
 
 * Your NestedIterator object will be instantiated and called as such:
 *     NestedIterator i = new NestedIterator(nestedList);
 *     while (i.hasNext())
 *         list.add(i.next()); */

import java.util.Iterator;

public class NestedIterator implements Iterator<Integer> {

    ArrayList<Integer> intValues;
    int curPos;
    
    // Constructor of the Iterator
    public NestedIterator(List<NestedInteger> nestedList) {
        this.intValues = flattenTheListOfNestedIntegers(nestedList);
        this.curPos = -1; // 执行了next以后才是第一个，即才是编号为0的元素
    }
    
    private ArrayList<Integer> flattenTheListOfNestedIntegers(List<NestedInteger> nestedList) {
        
        ArrayList<Integer> result = new ArrayList<>();
        Queue<NestedInteger> niQueue = new LinkedList<>();
        
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                result.add(ni.getInteger());
            } else { // is a nested list
                result.addAll(flattenTheListOfNestedIntegers(ni.getList()));
            }
        }
        return result;
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        this.curPos ++;
        return intValues.get(curPos);
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        return (curPos < intValues.size() - 1);
    }

    @Override
    public void remove() {}
}

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


// 方法1：用Stack做。不在一开始就全部彻底展开。非常巧妙的方法 ！！！ 要记住 ！！！
// Ref: http://www.jiuzhang.com/solutions/flatten-nested-list-iterator/
import java.util.Iterator;

public class NestedIterator implements Iterator<Integer> {
 
    private Stack<NestedInteger> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        pushListToStack(nestedList);
    }
    // 这个 helper method 使用了一个局部的 helper stack，把list里的 Nested Integers 来回倒腾了 2次
    private void pushListToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> temp = new Stack<>();
        for (NestedInteger nested : nestedList) {
            temp.push(nested);
        }
        
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    // 这里的 hasNext()，实际上还对数据做了操作整理 ！！！
    // 如果当前的栈顶的 NestedInteger，不是一个 Integer，而是一个 List of NestedIntegers，则我们要不断地展开它 ！！！
    // 直到栈顶的 NestedInteger 是一个 Integer ！！！
    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        // 注意 ！ 这里要用 while loop 而非 if 判断 ！
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushListToStack(stack.pop().getList()); // 注意 ！ 这里要 get List ！！！
        }
        
        return !stack.isEmpty();
    }
    
    @Override
    public void remove() {}
}


// 方法2：不好的方法：一开始就把整个List of NestedInteger 全部彻底展开为 ArrayList of Integer
// 这个方法在开始之前，就用 O(n) 的时间和 O(n) 的空间把原数据处理了一遍，这样做非常不 professional 啦 ！！
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

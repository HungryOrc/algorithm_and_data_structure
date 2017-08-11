/* Given several 1d vectors, implement an iterator to return their elements alternately.
For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next 
should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 
For example, given the following input:
[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].

Your ZigzagIterator object will be instantiated and called as such:
    ZigzagIterator i = new ZigzagIterator(v1, v2);
    while (i.hasNext()) 
        v[f()] = i.next(); */


// 输入的是 List<Iterator<Integer>>。这比输入 List<List<Integer>> 会难一些。

public class ZigZagIterator {
    List<Iterator<Integer>> listOfIters;
    int curIterIndex;
    Integer curValue;
    
    // constructor
    public ZigZagIterator(List<Iterator<Integer>> listOfIters) {
        this.listOfIters = listOfIters;
        this.curIterIndex = 0;
        this.curValue = null;
    }
    
    public boolean hasNext() {
        if (curValue != null) { // 诀窍 ！！！ 别忘了 ！！！
            return true;
        }

        int numOfNotEmptyIters = listOfIters.size(); // 这个count是又一个诀窍 ！！！ 用在后面的 while loop 里 ！！！
        while (numOfNotEmptyIters > 0) {
            
            if (curIterIndex == listOfIters.size()) {
                curIterIndex = 0; // loop回到第一行。又是一个诀窍 ！！！
            }
            
            Iterator<Integer> curIter = listOfIters.get(curIterIndex);
            curIterIndex ++;
            
            if (!curIter.hasNext()) {
                numOfNotEmptyIters --;
            } else {
                curValue = curIter.next();
                return true;
            }
        }
        return false;
    }
    
    public Integer next() {
        if (this.hasNext()) {
            Integer result = curValue;
            curValue = null; // 别忘了这个 ！！！
            return result;
        }
        return null;
    }
}

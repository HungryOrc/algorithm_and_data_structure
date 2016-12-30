/* Given two 1d vectors, implement an iterator to return their elements alternately.
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

// ？？？？？
// 以下我的方法的拓展性并不好！看一下答案里更好的方法！
public class ZigzagIterator {

    List<Integer> l1;
    List<Integer> l2;
    int curTurn;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) 
    {
        this.l1 = v1;
        this.l2 = v2;
        this.curTurn = 1;
    }

    public int next() 
    {
        if (l1.size() == 0)
            return l2.remove(0);
        else if (l2.size() == 0)
            return l1.remove(0);
        else if (curTurn == 1)
        {
            curTurn = 2;
            return l1.remove(0);
        }
        else // if (curTurn == 2)
        {
            curTurn = 1;
            return l2.remove(0);
        }
    }

    public boolean hasNext() 
    {
        if (l1.size() > 0 || l2.size() > 0)
            return true;
        else
            return false;
    }
}


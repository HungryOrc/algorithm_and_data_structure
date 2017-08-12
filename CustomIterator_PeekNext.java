/* Make an Iterator that can peek the next value without moving the pointer to the next position.
For example:
{1, 2, 3, 4}
iter.peek();  // 1
iter.next();  // 1
iter.peek();  // 2
iter.peek();  // 2
iter.next();  // 2
iter.next();  // 3
iter.peek();  // 4    */

public class OddIterator {
    List<Integer> list;
    Iterator<Integer> iter;
    // 因为将会使用list自带的iterator，所以不再设置 int curIndex ！！！
    // 不过专门设了一个 next value，如下。具体作用看后面代码
    Integer nextValue;

    // constructor
    public OddIterator(List<Integer> input) {
        list = input;
        iter = input.Iterator();
        nextValue = null; // 这个不写也是默认null，写是为了更清楚
    }
    
    // hasNext() 这里不仅判断了后面还有没有数，而且还做了往下走一步的动作 ！！！
    // Java自己的库里面一般也是在 hasNext() 里面做尽量多的是，next() 只是把已经存好的结果拿出来
    public boolean hasNext() {
        if (nextValue != null) { // 重要 ！！！
            return true;
        }
        
        if (iter.hasNext()) {
            nextValue = iter.next();
            return true;
        }
        return false;
    }
    
    // next() 这里只是取出之前存好的数 nextValue，并没有往下走
    public Integer next() {
        if (this.hasNext()) {
            Integer result = nextValue;
            nextValue = null; // 别忘了这个 ！！！
            return result;
        }
        return null;
    }
    
    // 这一题的新意在此函数 ！！！
    public Integer peek() {
        if (this.hasNext()) {
            return nextValue;
        }
        return null;
    }
}

// 从一个list里面，每k个数里取一个数。比如k = 4 的话，就是每次跳过3个数，取第4个数，以此类推。

public class JumpIterator {
    List<Integer> list;
    Iterator<Integer> iter;
    // 因为将会使用list自带的iterator，所以不再设置 int curIndex ！！！
    // 不过专门设了一个 cur value，如下。具体作用看后面代码
    Integer curValue;
    int k; // 每k个数里面取1个

    // constructor
    public JumpIterator(List<Integer> input, int k) {
        list = input;
        iter = input.Iterator();
        curValue = null; // 这个不写也是默认null，写是为了更清楚
        this.k = k;
    }
    
    public boolean hasNext() {
        if (curValue != null) { // 重要 ！！！
            return true;
        }
        
        Integer tmp = null; // 关键 ！！！ 这里要再设一个变量，作为 curValue 的前锋 ！！！ 下面这些代码都很重要
        for (int i = 0; i < this.k; i++) {
            if (iter.hashNext()) {
                tmp = iter.next();
            } else {
                return false;
            }
        }
        curValue = tmp;
        return true;
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

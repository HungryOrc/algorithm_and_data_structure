/* List有自带的Iterator。我们可以利用它自带的这个Iterator，来实现一个method，以打印出一个List<Integer>里面的所有奇数：

public void oddIterator(List<Integer> list) {
    
    Iterator<Integer> iter = list.Iterator(); // default iterator of list class in java
    
    while (iter.hasNext()) {
        Integer nextNum = iter.next();
        if (nextNum % 2 == 1) { // 是奇数
            System.out.print(nextNum + " ");
        }
    }
}

现在要求做的，是自定义一个class，来实现这个专门挑奇数的Iterator   */


// 方法1：不采用list自带的默认iterator。代码简明一些。缺点是会直接接触 raw data，就是原list，没有做到很好的防火墙隔离
public class OddIterator {
    List<Integer> list;
    int curIndex;

    // constructor
    public OddIterator(List<Integer> input) {
        list = input;
        curIndex = 0;
    }
    
    public boolean hasNext() {
        // 如果当前没到list的尾巴，且当前的数为偶数，则忽视，看下一个数
        while (curIndex < list.size() && list.get(curIndex) % 2 == 0) {
            curIndex ++;
        }
        return curIndex < list.size();
    }
    
    public Integer next() {
        // 注意 ！！！ Iterator的next()函数一定要永远要保护在hasNext()函数的里面 ！！！
        if (this.hasNext()) {
            Integer result = list.get(curIndex);
            curIndex ++;
            return result;
        }
        
        return null;
    }
}


// 方法2：使用list自带的默认iterator，在这个基础上构建我们的iterator，主要的好处是不直接接触原list，安全性高
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
        
        while (iter.hasNext()) {
            Integer tmp = iter.next();
            if (tmp % 2 == 1) {
                nextValue = tmp;
                return true;
            }
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
}

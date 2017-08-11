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

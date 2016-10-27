/* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window. For example:
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

Your MovingAverage object will be instantiated and called as such:
MovingAverage obj = new MovingAverage(size);
double param_1 = obj.next(val);   */


// 笨办法：用ArrayList按顺序存数，新来的排尾部，最老的在头部
public class MovingAverage {

    ArrayList<Integer> numbers;
    int maxSize;
    double curAvg;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.maxSize = size;
        this.numbers = new ArrayList<Integer>();
    }
    
    public double next(int val) {
        int actualSize = numbers.size();
        
        // 注意：ArrayList在尾部加元素的方式：add(e)
        numbers.add(val);
        
        if (actualSize < this.maxSize)
        {
            this.curAvg = (double)(this.curAvg * actualSize + val) / 
                          (double)(actualSize + 1);
        }
        else
        {
            // 注意：ArrayList取某个位置的元素值的方式：get(pos)
            int numToBeRemoved = numbers.get(0);
            // 注意：ArrayList删除某个位置的元素的方式：remove(pos)
            numbers.remove(0);
            this.curAvg = (double)(this.curAvg * this.maxSize - numToBeRemoved + val) /
                          (double)this.maxSize;
        }
        return this.curAvg;
    }
}


// 较巧妙的办法：用数组存数，关键之处在于不断移动读写的位置：移除一个数就在其原位置上加入最新的数；
// 而这个读写位是不断向后移动的，移动到maxSize则用 %maxSize 来充值此位置，然后继续搞
// Runtime: O(1)，明显比上面的方法快很多
//
public class MovingAverage {

    int[] numbers;
    int maxSize;
    int curSize;
    int curPos_RW;
    int curSum;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.maxSize = size;
        this.numbers = new int[this.maxSize];
        this.curSize = 0;
        this.curPos_RW = 0;
        this.curSum = 0;
    }
    
    public double next(int val) {
        
        if (curSize < maxSize)
        {
            curSize ++;
        }
        else
        {
            curSum -= numbers[curPos_RW];
        }
        numbers[curPos_RW] = val;
        curPos_RW = (curPos_RW + 1) % maxSize;
        
        curSum += val;
        return (double)curSum / (double)curSize;
    }
}

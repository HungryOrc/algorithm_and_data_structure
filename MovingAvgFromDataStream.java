/* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window. For example:
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

Your MovingAverage object will be instantiated and called as such:
MovingAverage obj = new MovingAverage(size);
double param_1 = obj.next(val);   */


xun huan shu zu

// Runtime: O(1)，明显比上面的方法快很多
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

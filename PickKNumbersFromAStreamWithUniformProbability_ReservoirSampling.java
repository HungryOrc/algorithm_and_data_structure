/* Consider an unlimited flow of data elements. How do you sample k element from this flow, 
such that at any point during the processing of the flow, 
you can return a random set of k elements from the n elements read so far. 
Assumptions: k >= 1

You will implement two methods for a sampling class:
(1) read(int value) - read one number from the flow
(2) sample() - return at any time the k samples as a list, 
return the list of all values read when the number of values read so far <= k. */

这一题的特点：
数据是以stream的方式不断地，无限地流过来的
我们要选的是k个数。现有的已经流过来的所有数，被选中的概率必须都一样 */

/* 思路：详细的解释，见我总结的另一题：Pick A Number From A Stream With Uniform Probability_Reservoir Sampling
本题和上面这题的区别在于，要找k个数组成sample list，而不是仅找一个数作为sample。
具体的做法是：
如果之前stream过来的数还不到k个，则统统加到sample list里面去。
如果之前stream过来的数已经大于等于k个，设为共有n个数（包括最新刚到的那个数），则在0到n-1之间random出来一个int姑且命名为roll，
若此roll在[0, k)的范围内，则将sample list里面 index = roll 的数换成最新来的那个第n个数。
可以证明，这样的做法，可以保证n个数中每一个数被选入sample list 的概率都是 k/n ！！！   */

public class Solution {
  private final int k;
  private List<Integer> samples;
  private int streamCount;
  
  public Solution(int k) {
    this.k = k;
    this.samples = new ArrayList<Integer>();
    this.streamCount = 0;
  }
  
  public void read(int value) {
    streamCount ++;
    if (samples.size() < k) {
      samples.add(value);
    } else {
      int roll = (int)(Math.random() * streamCount);
      if (roll < k) { // if roll is within [0, k)
        samples.set(roll, value);
      }
    }
  }
  
  public List<Integer> sample() {
    return this.samples;
  }
}

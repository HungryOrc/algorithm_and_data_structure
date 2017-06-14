/* Consider an unlimited flow of data elements. How do you sample one element from this flow, 
such that at any point during the processing of the flow, you can return a random element from the n elements read so far.

You will implement two methods for a sampling class:
(1) read(int value) - read one number from the flow.
(2) sample() - return at any time the sample, if n values have been read, the probability of returning any one of the n values is 1/n, 
return null(Java)/INT_MIN(C++) if there is no value read so far.

这一题的特点：
数据是以stream的方式不断地，无限地流过来的
现有的已经流过来的所有数，被sample选中的概率必须都一样 */

/* 思路：
按照数据的数量从小到大分析。当只有一个数时，我们在0到0之间random出来一个int，因为只有一个选择，所以怎么选都是这个数。
当有2个数时，我们在0到1之间random出来一个int，如果是0，则取最新的数即第二个数，如果不是0，则sample还是不变，还是之前选取的sample。
…… 
当有n个数时，我们在0到n-1之间random出来一个int，如果是0，则取最新的第n个数作为sample，否则sample还是维持为之前的值不变。
很容易证明，这么做，可以保证n个数里每一个数被选中作为sample的概率都一样是1/n ！！！

另外，还有一处很巧妙的地方！！！按照上面这个方法，我们其实不用存储所有的数，我们只用存储当前的sample值，和最新来的数的值，
这两个值就可以了 ！！！
详细的处理见下面的代码 */

public class Solution {
  int size;
  Integer curSample;
  
  public Solution() {
    size = 0;
    curSample = null;
  }
  
  public void read(int value) {
    size ++;
    int roll = (int)(Math.random() * size);
    if (roll == 0) {
      curSample = value;
    }
  }
  
  public Integer sample() {
    return curSample;
  }
}

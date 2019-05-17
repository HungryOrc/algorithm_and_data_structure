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
Reservior Sampling 的做法：当有n个数时，我们在0到n-1这n个数之间random出来一个int，如果是0，则取最新的第n个数作为sample，否则sample还是维持为之前的值不变

* 可以看出，对于第n个数，它被取的概率等于0到n-1里取到0的概率，也就是说必然是 1/n 的概率。
* 对于前n-1个数呢？因为我们说了，如果不random到0，则维持原来的sample不变，那么原来的n-1个数每个数被取为sample的概率都是1/(n-1)，另一方面，
我们不random到0的概率是(n-1)/n，所以二者乘到一起是 1/n，就是说原来的n-1个数被取为最终的sample的概率都是 1/n

按照数据的数量从小到大分析。当只有一个数时，我们在0到0之间random出来一个int，因为只有一个选择，所以怎么选都是这个数。
来了第2个数的时候，我们在0到1之间random出来一个int，如果是0，则取最新的数即第二个数，如果不是0，则sample还是不变，还是之前选取的sample
来了第3个数的时候，前两个数的概率是
…… 
当有n个数时，我们在0到n-1之间random出来一个int，如果是0，则取最新的第n个数作为sample，否则sample还是维持为之前的值不变。


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

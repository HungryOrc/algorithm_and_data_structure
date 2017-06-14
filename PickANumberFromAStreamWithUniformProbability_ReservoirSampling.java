/* Consider an unlimited flow of data elements. How do you sample one element from this flow, 
such that at any point during the processing of the flow, you can return a random element from the n elements read so far.

You will implement two methods for a sampling class:
(1) read(int value) - read one number from the flow.
(2) sample() - return at any time the sample, if n values have been read, the probability of returning any one of the n values is 1/n, 
return null(Java)/INT_MIN(C++) if there is no value read so far.

这一题的特点：
数据是以stream的方式不断地，无限地流过来的
现有的已经流过来的所有数，被sample选中的概率必须都一样 */


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

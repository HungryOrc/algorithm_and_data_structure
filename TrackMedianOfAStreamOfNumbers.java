/* Given an unlimited flow of numbers, keep track of the median of all elements seen so far.
You will have to implement the following two methods for the class:
(1) read(int value) - read one value from the flow
(2) median() - return the median at any time, return null if there is no value read so far

Examples:
read(1), median is 1
read(2), median is 1.5
read(3), median is 2
read(10), median is 2.5
......   */

/* 思路：很巧妙。用2个Heap，一个min heap 来维持较大的一半的数，一个max heap 来维持较小的一半的数。
然后我们要维持2个heap的size的大小，做到 max heap 的size 要么等于 min heap 的size，要么比min heap 的size 大一。具体如下：

来了一个新的数value的时候：
(1) 如果此时max heap size == min heap size
if max heap 为空（即 min heap 也为空），则把 value 放到 max heap里去
else if value的值 小于等于 max heap顶部的值，即小于等于较小的一半里的最大值，则也是 把value放到max heap里即较小的一半里去
else if value的值 大于 max heap顶部的值，即大于较小的一半里的最大值，则 
先把value放到 min heap即较大的一半里，再把较大的一半里的最小值放到较小的一半里去
(2) 如果此时max heap size == min heap size + 1
if value的值 小于等于 max heap顶部的值，即小于等于较小的一半里的最大值，则
先把value放到max heap即较小的一半里，再把较小的一半里的最大值放到较大的一半里去
if value的值大于 max heap顶部的值，即大于较小的一半里的最大值，则把value放到min heap里即较大的一半里去

最后求median的时候，如果2个heap的size相同，则取2个heap的顶部的值再除以2.0；
如果较小的一半比较大的一半多一个数，则取较小的一半的顶部的值，即max heap的顶部的值。  */

public class Solution {
  private PriorityQueue<Integer> smallerHalf;
  private PriorityQueue<Integer> largerHalf;
  
  public Solution() {
    // min heap
    largerHalf = new PriorityQueue<>();
    // max heap
    // Attension!!! Max Heap can be constructed by merely: Collections.reverseOrder() !!!
    // "11" is just a commonly used default initial size of Priority Queues
    smallerHalf = new PriorityQueue<>(11, Collections.reverseOrder());
  }
  
  public void read(int value) {
    if (smallerHalf.size() == largerHalf.size()) {
      if (smallerHalf.isEmpty() || value <= smallerHalf.peek()) {
        smallerHalf.offer(value);
      } else { // value > smallerHalf.peek()
        largerHalf.offer(value);
        smallerHalf.offer(largerHalf.poll());
      }
    } 
    else { // smallerHalf.size() == largerHalf.size() + 1
      if (value <= smallerHalf.peek()) {
        smallerHalf.offer(value);
        largerHalf.offer(smallerHalf.poll());
      } else { // value > smallerHalf.peek()
        largerHalf.offer(value);
      }
    }
  }
  
  public Double median() {
    if (smallerHalf.size() + largerHalf.size() == 0) {
      return null;
    }
    else if (smallerHalf.size() == largerHalf.size()) {
      return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
    } 
    else { // smallerHalf.size() == largerHalf.size() + 1
      return (double)smallerHalf.peek();
    }
  }
}

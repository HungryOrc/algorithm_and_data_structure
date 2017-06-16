/* Given a sorted integer array, remove duplicate elements. 
For each group of elements with the same value do not keep any of them. 
Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. 
Return the array after deduplication.
Assumptions: The given array is not null

Examples:
{1, 2, 2, 3, 3, 3} → {1}
{4, 4, 4, 4} → {}
{1, 2, 2, 3, 3, 3, 1} → {1, 1}   */

// 快慢双指针。同向而行
public class Solution {
  
  public int[] dedup(int[] array) {
    if (array == null || array.length <= 1) {
      return array;
    }
    
    // array[slow - 1] is the latest sample we encountered that might be
    // duplicated or not
    int slow = 1, fast = 1;
    
    while (fast < array.length) {
    
      // 这一题的解法的关键在于，把 “当前重复” 和 “当前没重复” 这两种情况，分别放在两个 “处理包” 里 ！！！
      
      // 1. 发现当前重复 的处理包：
      if (array[fast] == array[slow - 1]) {
        while (fast < array.length && array[fast] == array[slow -1]) {
          fast ++;
        }
        // 这句话放在“处理包”里做就比较好，是一个整体的一部分
        // 不“打包”的话，散在外面，就不好处理slow的退一步和之前的while loop里fast的多次进步的  配  套  关  系 ！！！
        slow --; 
      }
      
      // 2. 当前没重复 的处理包：
      if (fast < array.length && // 别忘了还是得先看一下fast出了上界没有。没出的话才有以下的处理的必要
          (slow == 0 || // 注意这种可能性 ！ slow 可能已经退到数组的第一位了，slow - 1 就出下界了 ！！！
           array[fast] != array[slow -1])) {
        
        array[slow] = array[fast];
        slow ++;
        fast ++;
      }
    }
    
    // Arrays.copyOf 函数里的第二个参数(int)的意义是substring的长度（从index 0开始计算），
    // 而非substring的结束点的index或者结束点的后一位的index
    return Arrays.copyOf(array, slow); // index slow is excluded, so the length shall be equals "slow"
    // 注意：到了这里，slow可能==0，即例如 input array = {4, 4, 4, 4} 的情况
  } 
}

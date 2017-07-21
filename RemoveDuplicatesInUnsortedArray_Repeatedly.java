/* Repeatedly remove all adjacent, repeated characters in a given string from left to right.
No adjacent characters should be identified in the final string.

Examples:
(1) "abbbaaccz" → "a aaccz" → "ccz" → "z" 
    注意！
    3个重复的b，全都删除了，没有一个b留下
    一开始不相邻的1个a和后面的2个a，因为b的消除，连接在一起了，然后这3个a在下一步里都被删除了
    2个连续在一起的c也都被删除了
(2) "aabccdc" → "bccdc" → "bdc" */


// 方法1：我自己的方法，用一个模拟的栈（用数组的左半段模拟一个栈的行为），再加上两个指针一快一慢，同向而行，
// 慢指针的左边不含慢指针自己，是栈里的内容，也就是最后要保留的sub array；快指针所指向的是当前正要被处理的元素。
//
// 惊人之处在于：
// 这个代码，与 Remove Duplicates in SORTED Array_REMOVE ALL 那一题的代码，是完全一致的 ！！！
//
// 注意体会它们之所以一致的原因：
// 本题是 unsorted array 然后要反复删除所有重复内容，包括一开始就相邻的重复内容，和一开始不相邻但是随着处理过程而被相邻的重复内容；
//     比如 {1, 4, 4, 4, 4, 1, 1} 这样的数组，把中间的4都“去重”以后，
//     slow指针在第一个4那里，它左边的第一个1是当前要保留的唯一内容；fast指针在第二个1那里，即所有4后面的第一个1；
//     我们比较fast和slow-1的内容，发现它们是相同的，所以fast及其之后的所有1，以及slow-1处的1，都要被去重的。
// 那一题是 sorted array 然后要删除所有相邻的重复的内容，因为一开始就排序好的，所以一开始不相邻的元素就不可能相等即不可能重复
//     比如 {1, 1, 4, 4, 4} 这样的数组，一开始slow和fast都在index 1那里，即都在第二个1那里，
//     slow-1和fast的元素是相等的，所以fast先要移动到第一个4那里去，以摆脱所有的1，
//     然后slow--，即slow=0，把所有元素都退空
// 这样可以看出，这两题内在的逻辑是一致的 ！！！

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


// 方法2：Laioffer的方法。很巧妙。用数组的左半边模拟了一个栈 ！！！
// 空间上 in place ！！！时间上 one pass ！！！不存在recursion
public class Solution {
  
    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
      
        char[] cArray = input.toCharArray();
      
        // use the left side of the char array as a "stack",
        // the index "end" in the array marks the top of the stack
        // end = 0 means the 1st element in the arary, end = -1 means the stack is empty
        int end = 0;
        // loop starting from the 2nd element in the array (i=1)
        for (int i = 1; i < cArray.length; i++) {
            
            // if the stack is empty (end == -1), or 当前的比较对象没有重复,
            // we push a new char into the stack
            if (end == -1 || cArray[i] != cArray[end]) {
                end ++;
                cArray[end] = cArray[i];
            }
            // else if we find the 1st occurence of duplication, 
            else {
                // we must pop the top element of the stack, namely the 1st element that is duplicated, 
                end --;
                // and then ignore all the consecutive duplicated chars that follows
                // 注意！下面这个也能处理 本来不相连但后来相连了的两段相同的char的情况 ！！！
                while (i < cArray.length - 1 && cArray[i] == cArray[i + 1]) {
                    i ++;
                }
            }
        }
      
        return new String(cArray, 0, end + 1);
    }
}

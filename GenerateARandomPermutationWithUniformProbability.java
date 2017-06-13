/* Given an array of integers (without any duplicates), 
shuffle the array such that all permutations are equally likely to be generated.
Assumptions: The given array is not null   */

/* 这一题的要求就是：给一些数字，生成关于这些数字的一个排列，必须满足，任何排列被生成的概率都是一样的 ！！

方法：设一共有n个数，
用1/n的概率拿第一个数，那么所有数被拿的概率都是1/n；
然后在剩下的n-1个数里，用1/n-1的概率拿第二个数，那么剩下的所有数被拿的概率是 (1/(n-1)) * ((n-1)/n) = 1/n，
其中((n-1)/n)是这些数没有在第一轮被拿的概率，所以剩下的每个数被拿的概率还是 1/n；
然后在剩下的n-2个数里，用1/n-2的概率拿第三个数……
可以类似地证明，每个数在每一步被拿的概率都是1/n，即每个数被安排在每一个位置的概率都是1/n。
所以，这样生成出来的一个排列，能够保证，每一种排列被生成出来的概率都是相等的 ！！！ 

然后具体到这一题的做法，就是先用1/n的概率，在0到n-1的位置之间（即整个数组上）找一个数，然后把它swap到数组的末尾，即index为n-1的位置，
然后再用1/(n-1)的概率，在0到n-2的位置之间找一个数，然后把它swap到数组里index为n-2的位置，
然后再用1/(n-2)的概率，在0到n-3的位置之间找一个数，然后把它swap到数组里index为n-3的位置……
这样的做法，能保证每一种可能的排列，被“选中”即被生成出来的概率都是相同的 */

public class Solution {
  
  public void shuffle(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }

    for (int max = array.length; max >= 2; max--) {
      // random is exclusive in the upper bound !!!!!!
      int randNum = 0 + (int)(Math.random() * max);
      swap(array, randNum, max - 1);
    }
  }
  
  private void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}

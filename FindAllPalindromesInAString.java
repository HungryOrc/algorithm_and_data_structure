/* 对于一个给定的String，找里面所有的 palindrome substrings，要这些substrings的起始和终止indexes

很巧妙的方法 ！！！ DP
时间：O(n^2)。暴力方法需要 O(n^3)
空间：O(n^2)，就是DP矩阵

搞一个DP矩阵 boolean matrix[n][n]，n 是 input string 的长度。其中 matrix[i][j] represents if
input string 的第 i 个 char 到第 j 个 char（both inclusive）所组成的substring 是一个 palindrome
这相当于把一个一维问题化成了二维问题 ！！！ 但其实是简化了，而不是复杂化了 ！！！

对于任何 substring[i, j]，看它是不是palin，首先看i和j这两个char是否相等，然后看substring[i-1, j+1]是否是palin，这两个条件缺一不可。
然后这么往中间缩下去追溯的base case最后有2种，要么缩成一个char，要么缩成相邻的2个char。下面代码分别作了处理。

为了不必记录这个 boolean matrix 里面哪些元素是填过的，哪些是没填的，我很聪明地分别从两种base case开始填，从中间往两边扩 ！！！ */

private boolean[][] fillInPalinMatrix(String input) {
  char[] cArray = input.toCharArray();
  int n = cArray.length;
  boolean[][] palinMatrix = new boolean[n][n];

  // base case 1: substring[i, j]一共有奇数个字符
  for (int i = 0; i < n; i++) {
    palinMatrix[i][i] = true;

    int start = i - 1;
    int end = i + 1;

    while(start >= 0 && end < n) {
      if (cArray[start] == cArray[end] && palinMatrix[start + 1][end - 1]) {
        palinMatrix[start][end] = true;
      } else {
        palinMatrix[start][end] = false;
      }

      start --;
      end ++;
    }
  }

  // base case 2: substring[i, j]一共有偶数个字符
  for (int i = 0; i < n - 1; i++) {
    palinMatrix[i][i + 1] = (cArray[i] == cArray[i + 1]) ? true : false;

    int start = i - 1;
    int end = i + 2;

    while(start >= 0 && end < n) {
      if (cArray[start] == cArray[end] && palinMatrix[start + 1][end - 1]) {
        palinMatrix[start][end] = true;
      } else {
        palinMatrix[start][end] = false;
      }

      start --;
      end ++;
    }
  }

  return palinMatrix;
}

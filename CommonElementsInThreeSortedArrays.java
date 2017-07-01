/* Find all common elements in 3 sorted arrays.
Assumptions:
The 3 given sorted arrays are not null
There could be duplicate elements in each of the arrays

Examples:
A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]   */


// 方法1: 3个指针，每一次比较，最大的那个不动，另外两个如果小于最大的那个，都+1
// Time: O(m + n + p)，其中 m，n，p是3个数组分别的长度
public class Solution {
  
  public List<Integer> common(int[] a, int[] b, int[] c) {
    List<Integer> result = new ArrayList<>();
    int i = 0, j = 0, k = 0;
    
    while(i < a.length && j < b.length && k < c.length) {
      
      if (a[i] == b[j] && b[j] == c[k]) {
        result.add(a[i]);
        i ++;
        j ++;
        k ++;
        continue;
      }
      
      // 特别注意 ！！ 不要忘了先做好 i < a.length && j < b.length 这样的判断！
      // 因为在每一次的while loop的内部，也有一些index的++，有可能在内部就造成了越界！
      if (i < a.length && j < b.length && a[i] < b[j]) {
        i ++;
      } else if (i < a.length && j < b.length && a[i] > b[j]) {
        j ++;
      }
      
      if (j < b.length && k < c.length && b[j] < c[k]) {
        j ++;
      } else if (j < b.length && k < c.length && b[j] > c[k]) {
        k ++;
      }
    }
    return result;
  }
}


// 方法2：两步走。先找前两个数组的公共元素。找完以后，得到一个list。再看这个list与第三个数组之间的公共元素
// 这两步中的每一步，自然都是一个 Find Common Elements in 2 Sorted Arrays 问题
// Time: O(m + n + p)

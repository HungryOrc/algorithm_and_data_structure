/* Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
Assumptions:
In each of the two sorted arrays, there could be duplicate numbers.
Both two arrays are not null.

Examples:
A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]  */


// 方法1: 2个指针，谁小移谁
// 时间：O(m + n)，m 和 n 分别为2个数组的长度
public class Solution {
  
  public List<Integer> common(List<Integer> A, List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    
    int index1 = 0;
    int index2 = 0;
    
    while (index1 < A.size() && index2 < B.size()) {
      if (A.get(index1) < B.get(index2)) {
        index1 ++;
      } else if (A.get(index1) > B.get(index2)) {
        index2 ++;
      } else { // ==
        result.add(A.get(index1));
        index1 ++;
        index2 ++;
      }
    }
    return result;
  }
}


// 方法2：用HashSet来记录一个数组里出现过的元素。哪个数组短，就记录哪个数组
// 如果 m < n，则用set来记录数组m里的所有元素。最后的消耗为：
// 时间：O(m + n)
// 空间：O(m)


// 方法3：用binary search 做
// 如果 m < n, 则对于m里的每一个元素，do a binary search in array n
// 时间：O(m logn)

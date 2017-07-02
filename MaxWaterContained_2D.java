/* 给定 n 个非负整数 a1, a2, ..., an, 每个数代表了坐标中的一个点 (i, ai)。
找到两条线 (i, ai) 和 (j, aj)，使得其与 x 轴共同构成一个容器，以容纳最多水。

样例
给出[1,3,2], 最大的储水面积是2. 左边沿横坐标1纵坐标1，右边沿横坐标3纵坐标2，总宽度3-1=2，总高度1 */


/* 方法1：DP。两个DP数组，
   一个记录从左边第一个bar到当前bar的最高高度，另一个记录从右边第一个bar到当前bar的最高高度。
   然后对于当前bar所在的位置，所能容留的最高水位，就是上面两个DP数组在此点的值的更小的一个。
   时间：O(n)
   空间：O(n)     */

public class Solution {
  
  public int maxTrapped(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    
    int n = array.length;
    
    // 2 DP arrays
    int[] fromLeft = new int[n];
    fromLeft[0] = array[0];
    int[] fromRight = new int[n];
    fromRight[n - 1] = array[n - 1];
    
    for (int i = 1; i < n; i++) {
      fromLeft[i] = Math.max(fromLeft[i - 1], array[i]);
    }
    for (int i = n - 2; i >= 0; i--) {
      fromRight[i] = Math.max(fromRight[i + 1], array[i]);
    }
    
    int result = 0;
    for (int i = 0; i < n; i++) {
      int waterInThisSlot = Math.min(fromLeft[i], fromRight[i]) - array[i];
      result += waterInThisSlot;
    }
    return result;
  }
}


/* 方法2：很巧妙的方法！！！比方法1更快。但不那么容易理解

   以一个边为左边界或右边界，能构成的最大面积，必然是另一边是距离它最远，且不低于它的边
   那么双指针。left从0开始，right从length-1开始。
   如果height[right] >= height[left]，那么以left作为左边界的最大面积的右边界一定在当前right。
   所以我们就不必再考虑任何以left为左边界的矩形了。所以我们可以把left++即右移一位。
   如果left+1处的height大于等于right处的height，则同上理可知，以right为右边界的
   最大面积的左边界一定在当前的位置即left+1，不会有更大的面积的可能性了。
   所以我们不必再考虑任何以当前right为右边界的正方形。我们就可以把right--即左移一位。
   最终的全局max，必然在依次这么操作所得到的各个局域max中的一个  */
// Ref: http://www.jiuzhang.com/solutions/container-with-most-water/

public class Solution {

    public int maxArea(int[] heights) {
        
        if(heights == null || heights.length <= 1) {
            return 0;
        }
        
        int left = 0, right = heights.length - 1;
        int maxVolume = 0;
        
        while (left < right) {
            maxVolume = Math.max(maxVolume, calcVolume(heights, left, right));
            
            if (heights[left] <= heights[right]) {
                left ++;
            } else { // >
                right --;
            }
        }
        return maxVolume;
    }
    
    private int calcVolume(int[] heights, int left, int right) {
        // 注意这里要的height是min不是max
        return (right - left) * (Math.min(heights[left], heights[right]));
    }   
}

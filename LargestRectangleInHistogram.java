/* Histogram 是柱状图。求里面的最大面积的长方形
Ref: http://www.lintcode.com/en/problem/largest-rectangle-in-histogram/#

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram.
配图见本题的链接
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example
Given height = [2,1,5,6,2,3],
return 10. */

/* Ref: http://www.jiuzhang.com/solutions/largest-rectangle-in-histogram/
思路很巧妙！思考的切入点是：最大长方形的结束位置。

最大长方形可能的结束位置，如果是坐标[x]，那么height[x+1]一定要小于height[x]，
否则不管这个最大长方形的高度目前是多少，都一定可以至少再往右边增加一格，那么当前长方形就不是最大长方形。
即最大长方形的右端点的高度一定要 > height[x+1]

然后，对于每一个可能的结束位置，我们要考虑所有可能的高度，确定了可能的高度就自然确定了可能的宽度即这个长方形的左端点位置。
最大长方形的高度当然未必是右端点（终止位置）或者左端点（起始位置）的高度，而可能是整个长方形里任何一个横向坐标上的高度。
但是这个高度一定要 > height[x+1]！因为如果 <= height[x+1]，则最大长方形的结束点一定不会是在[x]，而是可以至少延长到坐标[x+1]。
所以最大长方形里  每  一  点  的高度，都要 > height[x+1]。
所以我们就要在高度 > height[x+1] 的条件下来确定长方形的高度。高度确定以后，宽度就会自然被确定。为何？看下面的图示：

=========================================  举 世 无 双 的 图 示  ================================================== 

我们从左往右逐个看各个柱子的高度。假设前几个都是单调递增的，所以什么也没发生：
（因为如果一个柱子x的右边紧邻的柱子比自己高的话，那么x是不可能成为最大长方形的右端点的）

        |
        |
      | |
      | |
    | | |
  | | | | 
  0 1 2 3  
（底部的数字是这个柱子在 int array里的index，柱子的高度由 "|" 的个数来表示）


==================================================================================================================

至此可知：stack里每push进来一个元素(curHeight)之前，stack中所有比它大的元素都会被清除
换句话说，stack顶部的元素一直都会是整个stack里最大的元素，之前的所有元素都一定小于等于他
这就叫  “递增栈” 或者 “单调栈” */

public class Solution {
    
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(0); // 左边第一个竖柱
        
        int maxArea = 0;
        
        // 第一步：取每一个可能作为最大长方形的右端的坐标
        // 下面的系数 i，标定的就是上文所述的 [x+1] 坐标
        // 就是说，在下面的每个for loop 里，index = i-1 处为当前设定的长方形的右端点所在处
        for (int i = 1; i <= height.length; i++) {
            
            // i == height.length 意味着 [x] == height.length - 1，即我们已经考察到了数组heights的最右边的一个，
            // -1 的作用是保证到最后一列的右边时（已经出界了），一定小于前面所有的heights，因为那些heights都是正数
            int curHeight = (i == height.length) ? -1 : height[i];
            
            // 第二步：对于上面的最右端坐标，取每一个可能的长方形高度。
            // 高度确定后，左端点就会自然被确定
            while (!indexStack.isEmpty() && height[indexStack.peek()] > curHeight) {
                
                // 在 i-1 处为右端点的前提下，最大长方形的一个可能的高度
                int possibleHeight = height[indexStack.pop()];
                
                // 第三步：确定可能的高度后，自然就有与之对应的唯一的最大化长方形面积的宽度可以被我们求得：
                
                // i - 1 - indexStack.peek() 是精华所在 ！！！ 特别是 indexStack.peek() 是精华中的精华 ！！！
                // i是当前的[x+1]，-1 表示了要把[x+1]本身所占的一位去掉，那么最右端就是[x]了，
                // indexStack.peek()是在当前的长方形高度的假设下，这个长方形往左延伸最多能延伸到的位置的再左一位 ！！！
                // 具体解释见首上部的图示
                
                // 另一方面，
                // 如果出现stack为空的情况，要么是程序最开始运行时，stack里一个元素也没有；
                // 要么是 curHeight 小于当前stack里的所有元素，它将使得所有元素都被pop出来，
                // 即，curHeight 是迄今为止最矮的柱，它促成的最大长方形的可能性之一是
                // 从坐标0到坐标[i-1]即坐标[x]贯穿的长方形
                
                int possibleWidth = indexStack.isEmpty() ? i : i - 1 - indexStack.peek();
                
                maxArea = Math.max(maxArea, possibleHeight * possibleWidth);
            }
            
            // 无论什么情况，都会把当前的index放到stack里去
            indexStack.push(i);
        }
        return maxArea;
    }
}

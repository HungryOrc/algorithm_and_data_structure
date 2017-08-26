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

一. 我们从左往右逐个看各个柱子的高度。假设前几个都是单调递增的，所以什么也没发生：
（因为如果一个柱子x的右边紧邻的柱子比自己高的话，那么x是不可能成为最大长方形的右端点的）
          |
        | |
        | |
      | | |
      | | |
    | | | |
  | | | | |
  0 1 2 3 4 
（底部的数字是这个柱子在 int array里的index，柱子的高度由 "|" 的个数来表示）

二. 在 index=5 处，来了一个新的 height=5，如下图，
它比 index=4 处的柱子（height=7）要短，所以 index=4 处的柱子是可能作为 最大长方形的右端点的。
我们以 index=4处就是最大长方形的右端点 为前提进行下一步的讨论。
右端点定了以后，我们要定长方形的可能的高度。从 index=4 处逐个往左看。
1. 如果长方形的高度等于 index=4 处的高度（即7），则可见这个长方形将终止于 index=3 的右边，因为index=3处的高度<7.
   （可以看到，把这些heights放到一个stack里去，然后pop出index=4之后，再peek就是index=3了）
   index=3在这里标示了长方形的左端点再往左一个。所以右端点在index=4，左端点也在index=4，高度为7的长方形的宽度为 1. 面积为 7.
   这一部分的讨论见左下图。
2. 继续向左看。如果长方形的高度等于 index=3 处的高度（即6），则这个长方形将终止于 index=2 的右边，因为index=2处的高度<6.
   （可以看到，在stack里pop出index=3之后，再peek就是index=2了。）
   所以这个长方形的右端点在 index=4，高度为index=3处的6，左端点在 index=3，宽度为2，面积为 12.
   这一部分的讨论见右下图。
3. 继续向左看。在index=2处，高度为4，小于右端点(index=4)再往右一位(index=5)处的高度5，
   这说明，右端点在index=4的最大长方形，左端点不可能在index=2。否则，右端点至少能向右延伸到index=5处。
所以，至此，右端点在index=4的最大长方形的所有可能的情况，我们都讨论完毕了。
   
      长方形高7            长方形高6
          ^                   ^ 
          :                     :
        | :                   : :
 ```````|`:`|```       ```````:`:`|```
      | | : |               | : : |
      | | : |               | : : |
    | | | : |             | | : : |
  | | | | : |           | | | : : |
  0 1 2 3 4 5           0 1 2 3 4 5
          右端点4               右端点4
          左端点4             左端点3
（注意！标为虚线即 : 的柱子，是已经从stack里被pop出去的柱子！！以后用peek来找左端点的时候，它们是不存在的即不可能被选上的 ！！！）

三. 在 index=6 处，来了一个新的 height=3，如下图，
它比 index=5 处的柱子（height=5）要短，所以 index=5 处的柱子是可能作为 最大长方形的右端点的。
右端点定了是 index=5，我们要定长方形的可能的高度。从 index=5 处逐个往左看。

这里特别注意 ！ index=3和4处的柱子，已经被pop过了 ！！！ 它们不再可能成为以 index=5 为右端点的长方形的高度 ！！！
原因：它们的高度都大于 index=5 处的柱子的高度。
而我们用stack来存储和处理这些柱子的方式，可以自然地保证，index=3和4 这些高度大于当前右端点柱子的高度的柱子，
不会再出现在当前的备选高度里面。是不是很惊奇？是不是很激动？

1. 如果长方形的高度等于 index=5 处的高度（即5），则可见这个长方形将终止于 index=2 的右边，因为index=2处的高度<5.
   （可以看到，在stack里pop出index=5之后，再peek就是index=2了。）
   所以这个长方形的右端点在 index=5，高度为index=5处的5，左端点在 index=3，宽度为3，面积为 15.
   这一部分的讨论见左下图。
2. 继续向左看。如果长方形的高度等于 index=2 处的高度（即4），则这个长方形将终止于 index=1 的右边，因为index=1处的高度<4.
   （可以看到，在stack里pop出index=2之后，再peek就是index=1了。）
   所以这个长方形的右端点在 index=5，高度为index=2处的4，左端点在 index=2，宽度为4，面积为 16.
   这一部分的讨论见右下图。
3. 继续向左看。在index=1处，高度为2，小于右端点(index=5)再往右一位(index=6)处的高度3，
   这说明，右端点在index=5的最大长方形，左端点不可能在index=1。否则，右端点至少能向右延伸到index=6处。
所以，至此，右端点在index=5的最大长方形的所有可能的情况，我们都讨论完毕了。

      长方形高7            长方形高6
          ^                   ^ 
          :                     :
        | :                   : :
 ```````|`:`|```       ```````:`:`|```
      | | : |               | : : |
      | | : |               | : : |
    | | | : |             | | : : |
  | | | | : |           | | | : : |
  0 1 2 3 4 5           0 1 2 3 4 5
          右端点4               右端点4
          左端点4             左端点3
          
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

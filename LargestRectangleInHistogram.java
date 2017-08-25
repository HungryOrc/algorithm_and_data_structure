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

然后，对于每一个可能的结束位置，我们要考虑所有可能的起始位置。这样就和这个长方形的高度联系起来了。
最大长方形的高度当然未必是结束位置的高度height[x]，也未必是起始位置的高度，而可能是整个长方形
里任何一个横向坐标上的高度。
但是这个高度一定要 > height[x+1]！因为如果 <= height[x+1]，则最大长方形的结束点一定不会是在[x]，
而是可以至少延长到坐标[x+1]。
所以最大长方形里  每  一  点  的高度，包括 最左端点 和 最右端点 的高度，都要 > height[x+1]。
所以我们就要在高度 > height[x+1] 这个条件下寻找左端点。

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
        for (int i = 1; i <= height.length; i++) {
            
            // i == height.length 意味着 [x] == height.length - 1，即我们已经考察到了数组heights的最右边的一个
            // -1 的作用是保证到最后一列的右边时（已经出界了），一定小于前面所有的heights，因为那些heights都是正数
            int curHeight = (i == height.length) ? -1 : height[i];
            
            // 第二步：对于上面的最右端坐标，取每一个可能的最左端坐标
            // 最左端的坐标的确定其实是与整个长方形的宽度与高度联系在一起的
            while (!indexStack.isEmpty() && height[indexStack.peek()] > curHeight) {
                int possibleHeight = height[indexStack.pop()];
                
                // 在 i - 1 - indexStack.peek() 里，
                // i是当前的[x+1]，
                // -1表示了要把[x+1]本身所占的一位去掉，那么最右端就是[x]了
                // indexStack.peek()是目前正在被处理的(之前存到Stack里的)高度>height[x+1] 的index
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

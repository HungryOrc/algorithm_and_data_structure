// Given a matrix made up by '0's and '1's, find the largest (area) square surrounded by '1's.

/* 思路：参考我总结的 Largest Cross of Ones 那一题。这一题需要2个DP矩阵
矩阵1：从右往左计算每一行的最长连续1的个数。这样算出来是每一个cell作为左端点，在横向上最长连续1的个数
矩阵2：从下往上计算每一列的最长连续1的个数。这样算出来时每一个cell作为上端点，在纵向上最长连续1的个数
这里如果做4个方向的4个矩阵，是最齐全的，后面处理的方式也可以最自由。但其实只用做2个方向的矩阵就可以，后面的处理有个小trick就能hold住。详见下文

然后，对于matrix，从上往下，从左往右，看每一个cell，以这个cell为可能的长方形的左上角，
第一步，综合上面的矩阵1和矩阵2，看这个点同时向右和向下能延伸多长，注意，是同时延伸相同的长度，的最长的长度，这个长度设为m，
然后从 m 到 m-1 到 m-2 到 m-3 ...... 到 2 到 1，重复下面的2个步骤：
第二步，包含当前cell在内往右边走m个cell，是为长方形暂时的右上端点，根据矩阵2，看这个右上端点往下最多延伸多长
第三部，包含当前cell在内往下面走m个cell，是为长方形暂时的左下端点，根据矩阵1，看这个左下端点往右最多延伸多长
for each cell in the matrix as the top-left corner {
    for all possible edge length: m, m-1, ... 2, 1 {
        Check the top left corner, from right to left, from bottom to top
        Check the bottom left corner, from right to left
        Check the top right corner, from bottom to top
    }
}
时间复杂度：O(n^3)   */

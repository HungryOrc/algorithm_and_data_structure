/* https://leetcode.com/problems/rectangle-area/
Find the total area covered by two rectilinear rectangles in a 2D plane.
Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
Rectangle Area: Assume that the total area is never beyond the maximum possible value of int. */

// 公司：Math
public class Solution {
    
    // 自己做出来的，但错了两次：
    // 一是2个长方形之间可能有任何的相对位置关系，怎样用一个算式表达普适的位置关系。二是它们之间到底重叠没重叠，需要判断
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        
        int overlapArea;
        // if no overlap。注意这个不重叠的判断标准！！
        if (Math.min(C,G) <= Math.max(A,E)|| Math.min(D,H) <= Math.max(B,F))
            overlapArea = 0;
        else 
            overlapArea = (Math.min(C,G) - Math.max(A,E)) * (Math.min(D,H) - Math.max(B,F));
            
        int abcdArea = (C-A) * (D-B);
        int efghArea = (H-F) * (G-E);
            
        return abcdArea + efghArea - overlapArea;    
    }
}

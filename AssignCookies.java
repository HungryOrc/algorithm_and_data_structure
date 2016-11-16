/* Assume you are an awesome parent and want to give your children some cookies. 
But, you should give each child at most one cookie. Each child i has a greed factor gi, 
which is the minimum size of a cookie that the child will be content with; 
and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. 
Your goal is to maximize the number of your content children and output the maximum number.
Note:
* You may assume the greed factor is always positive. 
* You cannot assign more than one cookie to one child.

Example 1:
Input: [1,2,3], [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.

Example 2:
Input: [1,2], [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
*/

// 无法满足的崽子，就不用给任何cookie了，因为最后只要求满足的个数
public class Solution {
    
    // Greedy Algorithm
    // Ref: https://discuss.leetcode.com/topic/67676/simple-greedy-java-solution
    // 从最小胃的孩子开始，尝试用最小的cookie去满足最小胃的孩子。所以这是一个贪心算法，其局部最优解我们“认为”等于全局最优解……
    // Time: O(n*log(n))，这是排序的时间。处理的时间只有 O(n)
    public int findContentChildren(int[] g, int[] s) {
    
        Arrays.sort(g);
        Arrays.sort(s);
        int contentNum = 0;
        
        // 以饼干的个数为纲
        for (int i = 0, j = 0; i < s.length && j < g.length; i++)
        {
            if (s[i] >= g[j])
            {
                contentNum ++;
                
                // 注意！关键在此！！
                // 饼干的序号是每次循环都一定要 +1 的，而人的序号只有在找到match时才 +1 ！！
                j++;
            }
        }
        return contentNum;
    }
    
    
    // 一般的思路：
    // 从最大胃的孩子开始，尝试用最小的cookie去满足最大胃的孩子
    // Time: O(n^2)
    public int findContentChildren(int[] g, int[] s) {
    
        Arrays.sort(g);
        Arrays.sort(s);
        ArrayList<Integer> children = new ArrayList<>();
        ArrayList<Integer> cookies = new ArrayList<>();
        for (int child : g)
            children.add(child);
        for (int cookie : s)
            cookies.add(cookie);
            
        int contentNum = 0;
        for (int i = children.size()-1; i >= 0; i--)
        {
            if (cookies.size() > 0 && cookies.get(cookies.size()-1) < children.get(i))
                continue;
            for (int j = 0; j < cookies.size(); j++)
            {
                if (cookies.get(j) >= children.get(i))
                {
                    contentNum ++;
                    cookies.remove(j);
                    children.remove(i);
                    break;
                }
            }
        }
        return contentNum;
    }
}

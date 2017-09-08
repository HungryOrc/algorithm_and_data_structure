// https://www.hackerrank.com/challenges/crush/editorial

/* You are given a list of size n, initialized with zeroes. 
You have to perform m queries on the list and output the maximum of final values of all the n elements in the list. 
For every query, you are given three integers a, b and k 
and you have to add value k to all the elements ranging from index a to b(both inclusive).
比如：(1, 5, 100) 意味着在 index = 0 到 index = 4 的范围内，含两端点，每一个slot都 +100 
最终要求 return 整个range上的 max 高度   */


/* 思路：很巧妙 ！ O(n) 时间 ！！！
（注意 query的起始点a和终止点b都是从 index = 1 开始的，所以后面要 -1）
对于每一个 query，它其实都是在 index = a - 1 处开始拔高 height 值，在 index = b - 1 处是最后一处实施这个拔高，即
在 index = b 处这个拔高就终止了，
那么我们现在整个range数组上（一开始都是0），对于每一个query，在它们的起始点处 +height，在它们的终止点处的后一位 -height，
然后再从range数组的左边开始，逐个slot做 prefix sum，就可求到最高的 height   */

class Query {
	int start, end;
	int height;
	
	public Query(int s, int e, int a) {
		start = s;
		end = e;
		height = a;
	}
}

public class Solution {

	private static long getMax(Query[] queries, int n) {
		long[] range = new long[n];
		
		for (Query q : queries) {
			int startIndex = q.start - 1; // 别忘了 -1 ！
			int endIndex = q.end - 1; // 别忘了 -1！
			int height = q.height;
			
			range[startIndex] += height;
            
			// 注意 1：减去 amount，要在 end 后面一个slot减 ！ 不可以在end处减 ！！！
			// 注意 2：如果 end 已经是数组的最后一个index了，则不必再减了 ！！！
            if (endIndex < n - 1) {
    			range[endIndex + 1] -= height;
            }
		}
		
		long prefixSum = 0;
		long maxHeight = 0;
		
		for (long height : range) {
			prefixSum += height;
			maxHeight = Math.max(maxHeight, prefixSum);
		}
		return maxHeight;
	}
    

    public static void main(String[] args) {
        
        Query[] queries = new Query[3];
        queries[0] = new Query(1, 2, 10);
        queries[1] = new Query(2, 5, 20);
        queries[2] = new Query(3, 7, 35);
        
        System.out.println(getMax(queries, 10)); // 55
    }
}

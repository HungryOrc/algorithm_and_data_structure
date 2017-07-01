/* Given a dictionary containing many words, find the largest product of two words’ lengths, 
such that the two words do not share any common characters.
Assumptions:
The words only contains characters of 'a' to 'z'
The dictionary is not null and does not contains null string, and has at least two strings
If there is no such pair of words, just return 0

Examples:
dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)  */


/* 思路：用 max heap 来存不同的string pair的indexes，按照这个pair里的2个string的长度的乘积，来排列这个max heap。
当然，要这么做，首先题目给的string array必须是按照string的length降序排列的。
然后，把数组里的第一个和第二个数放到heap里去。因为是放indexes，所以放的是 0 和 1 这两个整数。
然后，只要heap不空，就从其顶部poll元素出来。每个元素是一个长度为2的整形数组，分别是2个string在初始的string array里的index，设为i和j。
如果i和j代表的这两个string不share任何common char，则记录下它们的长度的乘积，如果比maxProduct大，就更新maxProduct的值。

然后，要根据它expand两组接下来的pairs。分别是 i+1和j，以及 i和j+1 这两个pair。
不过，每一个index pair要放到heap里之前，都要满足以下几个条件：
两个index都没越界、两个index不相同、这个index pair没有被访问过、这个index pair所代表的string pair的长度乘积大于当前的 max product。

为了判断一个index pair是否被访问过，即去重的问题，我们用一个二维n*n的boolean matrix来做。
不用set来做，是为了避免写坑多的 equals，hashcode 等函数。

最后，这个方法里，为了判断两个string之间是否有common char，是用了 bit mask 来做。这个做法值得长期推广！
详情见最后一个helper function。


这个方法里必须注意的一个重要问题 ！！！
虽然我们用了max heap，但不能在得到一个不重复char的string pair以后，就直接return 它们的长度的乘积 ！！！
因为，我们的max heap，并不能保证先填进去的元素，就一定有相对最高的length product！
而只能保证，先填进去的元素，大概率有相对更高的 length product！
比如，index 为 1 和 2 的pair，会比 index 为 0 和 5 的pair 更早地被填入heap，但就 length product 来做，很可能后者更大 ！！！
所以这种方法，一定要做到尽头，做到 heap 空掉为止。不可中间结束。

但是相对于 双层循环 的暴力方法，用heap做的方法还是会快不少！
因为毕竟是从string length最长的头部开始做的。后面的各个pair，如果本pair的product不及当前的max product，就不会再考虑
任何比本pair还要更短的pairs了。所以这相当于减去了很多枝条！！！

这种方法的时间复杂度：O(n^2 * (m + 2logn))，其中n是dict里的string的个数，m是每个string的平均长度。
n^2是 number of pairs of strings。然后，
一方面，用heap pop元素的时候，heap最大是n^2的尺寸，所以每次pop都是log(n^2) 即 2logn 的时间复杂度；
另一方面，每次查看2个string有没有 common char，需要时间 O(m)。所以总的来说，是上面的表达式。 */

// 我的方法，代码虽然很长，但是思路其实很朴素，简明
public class Solution {
  
	public int largestProduct(String[] dict) {
		int n = dict.length;

		// sort the dict by length of the words in DESCENDING order
		Arrays.sort(dict, new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return 0;
				}
				return s1.length() < s2.length() ? 1 : -1;
			}
		});

		// the max heap which contains the pairs of indexes of the Strings in the dict array
		PriorityQueue<Integer[]> productMaxHeap = new PriorityQueue<Integer[]>(11, 
			new Comparator<Integer[]>(){
			@Override
			public int compare(Integer[] array1, Integer[] array2) {
				int product1 = array1[0] * array1[1];
				int product2 = array2[0] * array2[1];
				if (product1 == product2) {
					return 0;
				}
				return product1 < product2 ? 1 : -1; 
			}
		});

		// the map storing the bit mask values (ints) of the strings in the dict array
		HashMap<String, Integer> bitMasks = new HashMap<>();

		// the boolean 2D matrix, labeling the visited and unvisited pairs of indexes
		boolean[][] visited = new boolean[n][n];

		// result
		int maxProduct = 0;
		
		

		// the two indexes of the Strings in the dict array
		Integer[] pair = new Integer[]{0, 1};
		productMaxHeap.offer(pair);
		
		while (!productMaxHeap.isEmpty()) {

			Integer[] curPair = productMaxHeap.poll();
			int i1 = curPair[0];
			int i2 = curPair[1];
			String s1 = dict[i1];
			String s2 = dict[i2];
			
			visited[i1][i2] = true;
			visited[i2][i1] = true;

			Integer bitMask1 = bitMasks.get(s1);
			if (bitMask1 == null) {
				bitMask1 = getBitMask(s1);
				bitMasks.put(s1, bitMask1);
			}
			Integer bitMask2 = bitMasks.get(s2);
			if (bitMask2 == null) {
				bitMask2 = getBitMask(s2);
				bitMasks.put(s2, bitMask2);
			}

			// if s1 and s2 don't have any common char
			if ((bitMask1 & bitMask2) == 0) { 
				maxProduct = Math.max(maxProduct, s1.length() * s2.length());
			}

			// 如果s1和s2有重复的char，则expand接下来的一些pairs到heap里去
			int j1 = i1 + 1;
			int j2 = i2;
			if (j1 < n && j2 < n && j1 != j2 && visited[j1][j2] == false
				&& dict[j1].length() * dict[j2].length() > maxProduct) { // 明显长度乘积不够大的就不要做了
				productMaxHeap.offer(new Integer[]{j1, j2});
				
			}
			int k1 = i1;
			int k2 = i2 + 1;
			if (k1 < n && k2 < n && k1 != k2 && visited[k1][k2] == false
				&& dict[k1].length() * dict[k2].length() > maxProduct) {
				productMaxHeap.offer(new Integer[]{k1, k2});
			}
		}

		return maxProduct;
	}
	
	private int getBitMask(String s) {
		int bitMask = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			bitMask |= 1 << (c - 'a');
		}
		return bitMask;
	}
}

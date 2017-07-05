/* Given an array of Strings, check if all the strings can be chained to form a circle.
Two strings s1 and s2 can be chained iff the last char in s1/s2 is identical to the first char in s2/s1.

Example:
Input array = {"abc", "cd", "de"}, return false.
Input array = {"aaa", "bbb", "baa", "aab"}, return true.
Input array = {"abc", "c", "ca"}, return true.

Assumptions:
The input array is not null, and its length > 1.
There might be duplicate strings in the string array.
There might be strings composed of only 1 char, for example "c".  */


// 方法1：我的方法。经典 DFS
// （后面有 Laioffer 更快的 swap-swap 方法！！！）
// 时间：O(n * n^n)，  n 是 array of strings 的length。
//     因为对于n个string里的每一个，我们都要以它为开始点，看能否串成环，这是一个recursion过程。
//     然后对于每一个recursion过程，它的recursion tree都是n层的，每层最多有n个node，所以每一个recursion tree都是 O(n^n) 的时间
//     一共是n个这样的recursion tree。
// 空间：O(n)
//     hashmap要n空间，boolean array要n空间，call stack也是n层每层constant空间

// 代码看起来长，其实思路很朴素，很简明
public class Solution {
	 
	public boolean formCircle(String[] input) {
		Arrays.sort(input);	
		int n = input.length;
		
		// 这个能加快查找效率。确定一个string的尾char以后，看哪些strings的头char与之相同
		HashMap<Character, List<Integer>> startWith = new HashMap<>();
		for (int i = 0; i < n; i++) {
			char startChar = input[i].charAt(0);
			List<Integer> indexes = startWith.getOrDefault(startChar, new ArrayList<Integer>());
			indexes.add(i);
			startWith.put(startChar, indexes);
		}
		
		// input里的每一个string，都尝试作为整个string chain的第一个string
		for (int i = 0; i < n; i++) {
			if (i > 0 && input[i - 1].equals(input[i])) {
				continue;
			}
			if (formCircle(i, i, input, startWith, new boolean[n], 1)) {
				return true;
			}
		}
		return false;
	}
	 
	private boolean formCircle(int startIndex, int curIndex, String[] input, 
		HashMap<Character, List<Integer>> startWith, boolean[] visited,
		int linkedStrings) {
			 
		if (linkedStrings == input.length) {
				
			// 别忘了检查整个string chain的最后一string的last char 和最开始第一个string的
			// first char 是否相等 ！！！
			// 因此，这个recursion函数才需要maintain整个chain的最开始第一个string的index！
			if (input[startIndex].charAt(0) == input[curIndex].charAt(input[curIndex].length() - 1)) {
				return true;
			} else {
				return false;
			}
		}
			 
		visited[curIndex] = true;
			 
		String curString = input[curIndex];
		char lastChar = curString.charAt(curString.length() - 1);
		List<Integer> nextStringsIndexes = startWith.get(lastChar);
			
		// 别忘了这个！！ 有的词的结尾char可能没有任何词的起始char与之对应 ！！
		if (nextStringsIndexes != null) { 
			for (int index : nextStringsIndexes) {
				if (visited[index] == false) {
					if (formCircle(startIndex, index, input, startWith, visited, linkedStrings + 1)) {
						return true;
					}
				}
			}
		}
	 
		visited[curIndex] = false; // recover
	 
		return false;
	}
}


// 方法2：Laioffer 的 Swap-Swap 方法。很赞！！

public class Solution {
	 
	public boolean formCircle(String[] input) {
		Arrays.sort(input);	
		int n = input.length;
		
		// input里的每一个string，都尝试作为整个string chain的第一个string
		for (int i = 0; i < n; i++) {
			if (i > 0 && input[i - 1].equals(input[i])) {
				continue;
			}
			
			
			swap(input, 0, i);
			
			if (formCircle(input, 0)) {
				return true;
			}
			
			swap(input, 0, i);
		}
		return false;
	}
	 
	// 注意！Swap 做法不需要 boolean array 来监控 visited的情况 ！！！
	private boolean formCircle(String[] array, int curIndex) {
		
		// 特别注意 ！！！ 
		// 在这个方法里，cur index 到了 n - 1 的时候就要检查结束条件了 ！！！ 而非 到了 n 的时候再检查 ！！！
		if (curIndex == array.length - 1) {
				
			// 别忘了检查整个string chain的最后一string的last char 和最开始第一个string的 first char 是否相等 ！！
			if (array[0].charAt(0) == array[curIndex].charAt(array[curIndex].length() - 1)) {
				return true;
			} else {
				return false;
			}
		}
			 
		String curString = array[curIndex];
		char lastChar = curString.charAt(curString.length() - 1);

		for (int i = curIndex + 1; i < array.length; i++) {

			if (array[i].charAt(0) == lastChar) {
				
				swap(array, curIndex + 1, i);
				if (formCircle(array, curIndex + 1)) {
					return true;
				}
				swap(array, curIndex + 1, i); // recover
			}
		}
		return false;
	}

	private void swap(String[] array, int i, int j) {
		String tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}

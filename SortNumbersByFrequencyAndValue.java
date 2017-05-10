/* 将输入的数组进行排序，先按数组里的值出现的次数进行降序排列，相同次数的数之间再进行值的降序排列。如：
int[]{5, 0, 3, 1, 6, 5, -8, -8, 0, 0, 7, 3, 1, 2, 1, 1} 应被排列为：
1 1 1 1 0 0 0 5 5 3 3 -8 -8 7 6 2     */


// 方法1：用 HashMap + PriorityQueue
// Priority Queue 的每次 insert 耗时 logN，每次 poll 也耗时 logN
public class Solution {

	public int[] sortByFrequencyAndValue(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return nums;
		}
		
		int[] result = new int[nums.length];
		
		// <number, frequency>
		HashMap<Integer, Integer> records = new HashMap<>();
		for (int n : nums) {
			records.put(n, records.getOrDefault(n, 0) + 1);
		}
		
		// sort: frequency by descending order, and then value by descending order
		PriorityQueue<Map.Entry<Integer, Integer>> sortingPQ = 
			new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>(){
			@Override
			public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
				int compareFreq = entry2.getValue().compareTo(entry1.getValue());
				if (compareFreq != 0) {
					return compareFreq;
				}
				return entry2.getKey().compareTo(entry1.getKey());
			}
		});
		
		for (Map.Entry<Integer, Integer> entry : records.entrySet()) {
			sortingPQ.offer(entry);
		}
		
		int i = 0;
		while (!sortingPQ.isEmpty()) {
			Map.Entry<Integer, Integer> entry = sortingPQ.poll();
			for (int j = 0; j < entry.getValue(); j++) {
				result[i] = entry.getKey();
				i++;
			}
		}
		
		return result;
	}
}


// 方法2：用 HashMap + TreeSet
// Tree Set 是基于红黑树的！它的每次 insert 耗时 logN，但是它遍历所有node只需要 O(N)！！！这是它比 PriorityQueue 厉害的地方！！！
// 其实可以想到，作为一个树，自然有大把的遍历nodes的方式，可以在O(N)时间里把所有的节点读一遍
public class Solution {

	public int[] sortByFrequencyAndValue(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return nums;
		}
		
		int[] result = new int[nums.length];
		
		// <number, frequency>
		HashMap<Integer, Integer> records = new HashMap<>();
		for (int n : nums) {
			records.put(n, records.getOrDefault(n, 0) + 1);
		}
		
		// sort: frequency by descending order, and then value by descending order
		TreeSet<Map.Entry<Integer, Integer>> sortingTreeSet = 
			new TreeSet<>(new Comparator<Map.Entry<Integer, Integer>>(){
			@Override
			public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
				int compareFreq = entry2.getValue().compareTo(entry1.getValue());
				if (compareFreq != 0) {
					return compareFreq;
				}
				return entry2.getKey().compareTo(entry1.getKey());
			}
		});
		
		for (Map.Entry<Integer, Integer> entry : records.entrySet()) {
			sortingTreeSet.add(entry);
		}
		
		int i = 0;
		// 下面这样对 TreeMap 进行 for each 的操作，其实就是用的 TreeMap 的 Iterator ！！！
		// 这样做遍历所有nodes所需时间仅为 O(n)！！！
		for (Map.Entry<Integer, Integer> entry : sortingTreeSet) {
			for (int j = 0; j < entry.getValue(); j++) {
				result[i] = entry.getKey();
				i++;
			}
		}
		
		return result;
	}
}

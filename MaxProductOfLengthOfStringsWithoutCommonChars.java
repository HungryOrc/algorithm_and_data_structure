/* Given a dictionary containing many words, find the largest product of two words’ lengths, 
such that the two words do not share any common characters.
Assumptions:
The words only contains characters of 'a' to 'z'
The dictionary is not null and does not contains null string, and has at least two strings
If there is no such pair of words, just return 0

Examples:
dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)  */


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

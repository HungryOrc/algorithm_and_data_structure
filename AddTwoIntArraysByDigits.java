/* 加和2个int数组。每个数组里的每个元素都有可能是负数，也都有可能是多位数。加出来的结果要return为一个int数组，且这个结果数组里
的每一位都必须是一位数，且要体现正负号。比如：
int[] array1 = new int[]{1, 6, -125};
int[] array2 = new int[]{1, 1, 1};
上述二者加出来的结果必须是：
int[]{2, 7, -1, 2, 4}                    */


public class Solution {

	public Integer[] add(int[] array1, int[] array2) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < array1.length; i++) {
			int curSum = array1[i] + array2[i];
			list.addAll(convertToList(curSum));
		}
		
		return list.toArray(new Integer[0]); // 注意最后括号里的这个奇怪的格式 ！！！
	}
	
	private List<Integer> convertToList(int sum) {
		List<Integer> list = new ArrayList<>();
		
		// 如果这个sum的绝对值只有一位，那么不管正负，都直接返回即可
		if (Math.abs(sum) < 10) {
			list.add(sum);
			return list;
		}
		
		// 如果sum超过一位，则要逐位处理。首先要把正负号剥离出来
		boolean negative = sum < 0 ? true : false;
		sum = Math.abs(sum);
		
		while (sum > 0) {
			list.add(sum % 10);
			sum /= 10;
		}
		
		// 每一位都分隔开以后，再把最高的一位添回负号，如果一开始sum是负数的话
		if (negative) {
			int primeDigit = list.get(list.size() - 1);
			primeDigit *= -1;
			list.set(list.size() - 1, primeDigit);
		}
		
		// 必须reverse这个list再返回。因为上面的 while loop 做完后的效果是
		// 个位排在最左边，然后是十位，然后是百位…… 必须翻转过来才是正确结果
		reverse(list);
		return list;
	}
	
	private void reverse(List<Integer> list) {
		int i = 0, j = list.size() - 1;
		while (i < j) {
			int numI = list.get(i);
			list.set(i, list.get(j));
			list.set(j, numI);
			i++;
			j--;
		}
	}

	
	// test
	public static void main(String[] args) {

		Solution solu = new Solution();
		
		int[] array1 = new int[]{1, 6, -125};
		int[] array2 = new int[]{1, 1, 1};
		
		// 结果应该是：2 7 -1 2 4 
		Integer[] result = solu.add(array1, array2);
		
		for (int num : result)
			System.out.print(num + " ");
	}
}

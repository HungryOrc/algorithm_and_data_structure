// 给一个数组，及一个目标和，要在数组里找到2个元素，其和等于目标和
// 要求：返回一个符合要求的组合，里的各个元素的indices
// 元素可能重复，一个元素只能最多用一次

/* 注意！！！ 这一题要进行优化的话，要先问清楚，是时间上要优化，还是空间上要优化 ！！！
如果采用先排序再处理的方法，那么使用哪种排序算法，就和优化的目的有关了！
不是一定采用速度快的 quick sort 或者 merge sort！

因为从时间消耗来说，quick sort是最优 nlogn；merge sort是一定 nlogn；selection sort是一定 n^2，显然最差；
但如果是要优化空间效率！！！
那么从空间消耗来说，
merge sort是 n，因为它要搞新的数组出来放中间结果；
quick sort是O(height of tree)，比如O(logn)，因为它要call stacks，call stack的层数等于tree的高度，每一层消耗constant 的空间；
而selection sort只要双层循环，没有recursion，空间消耗是O(1)，从空间的角度说，selection sort 反而是最省的 ！！！
*/

public class TwoSum_ReturnOneCombination_ByElementIndices
{
	// 方法1：用 HashMap 找 targetSum - A[index]是否在数组中。不用先给数组排序了. 而是逐元素看是否满足求和，
	// 如果满足 targetSum，则 return 答案；不满足再添到 HashMap 里
	// Runtime: O(n)
	public int[] twoSum(int[] givenNumbers, int targetSum) {
    		int[] output = new int[2];
		// <value of num, index of num>
   		Map<Integer, Integer> myHashMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < givenNumbers.length; i++) {
        		if (myHashMap.containsKey(targetSum - givenNumbers[i])) {
            			output[0] = i;
            			output[1] = myHashMap.get(targetSum - givenNumbers[i]);
            			return output;
        		}
        		myHashMap.put(givenNumbers[i], i);
    		}
    		return output;
	}
	
	
	// 方法2：先排序数组，然后两边向中间逼近
  	// Runtime：O(n*logn)，其中排序用 O(n*logn)，两边向中间逼近用 O(n^2)
	//
	// indexLeft, indexRight 分别指向数组第一个元素和最后一个元素，判断两个元素的和 targetSum 的大小关系
	// 如果 A[indexLeft] + A[indexRight] == targetSum，那么找到两个下标返回即可
	// 如果 A[indexLeft] + A[indexRight] < targetSum，说明两个数的和还不够大，把 indexLeft 右移
	// 否则两个数和太大，把 indexRight 左移
	// 直到两个 index 交错
	public int[] twoSum(int[] givenNumbers, int targetSum)
    	{
		// put the indices of the 2 chosen numbers into one array for final output
		int[] output = new int[2];

		int indexLeft = 0;
		int indexRight = givenNumbers.length - 1;
		int numberLeft = 0;
		int numberRight = 0;

		// copy the given array
		int[] givenNumbers_Copy = new int[givenNumbers.length];
		for (int i = 0; i < givenNumbers.length; i++)
			givenNumbers_Copy[i] = givenNumbers[i];

		// "Dual-Pivot" Quick Sort
		// Sort the copy of the given array, from smaller to bigger
		Arrays.sort(givenNumbers_Copy);

		while (indexLeft < indexRight)
		{
		    if (givenNumbers_Copy[indexLeft] + givenNumbers_Copy[indexRight] == targetSum)
		    {
			numberLeft = givenNumbers_Copy[indexLeft];
			numberRight = givenNumbers_Copy[indexRight];

			// find the indexes of these 2 numbers in the original given array
			for (int i = 0; i < givenNumbers.length; i ++)
			{
				if (givenNumbers[i] == numberLeft)
				{
					output[0] = i;
					break;
				}
			}
			for (int i = givenNumbers.length - 1; i > 0; i --)
			{
				if (givenNumbers[i] == numberRight)
				{
					output[1] = i;
					break;
				}
			}
			break;

		    }
		    else if (givenNumbers_Copy[indexLeft] + givenNumbers_Copy[indexRight] < targetSum)
			indexLeft ++;
			
		    else
			indexRight --;
		}
		return output;
    	}
}

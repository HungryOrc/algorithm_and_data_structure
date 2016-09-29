import java.util.Arrays;
import java.util.HashMap;


// 给一个数组，及一个目标和，要在数组里找到2个元素，其和等于目标和
// 要求：返回一个符合要求的组合，里的各个元素的indices
// 元素可能重复，一个元素只能最多用一次
public class TwoSum_ReturnOneCombination_ByElementIndices
{
	// 方法：先排序数组，然后两边向中间调试
  	// Runtime：O(n*logn)
	//
	// indexLeft, indexRight 分别指向数组第一个元素和最后一个元素，判断两个元素的和 targetSum 的大小关系
	// 如果 A[indexLeft] + A[indexRight] == targetSum，那么找到两个下标返回即可
	// 如果 A[indexLeft] + A[indexRight] < targetSum，说明两个数的和还不够大，把 indexLeft 右移
	// 否则两个数和太大，把 indexRight 左移
	// 直到两个 index 交错
	// 
	public int[] twoSum_ByTwoIndices(int[] givenNumbers, int targetSum)
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
	
	
	// 方法：用 HashMap 找 targetSum - A[index]是否在数组中。不用先给数组排序了
	// Runtime: O(n)
	//
	// 对数组A进行线性扫描，索引为index，目标值为target，只需要判断 targetSum - A[index] 是否在数组中即可
	// 对于判断某个数是否存在可以用 HashMap 来降低时间复杂度
	//
	public int[] twoSum_ByHashMap(int[] givenNumbers, int targetSum)
	{
		HashMap<Integer, Integer> myHashMap = new HashMap<Integer, Integer>();
		int[] output = new int[2];

		for (int i = 0; i < givenNumbers.length; i++)
			myHashMap.put(givenNumbers[i], i);

		for (int i = 0; i < givenNumbers.length; i++)
		{
			if (myHashMap.containsKey(targetSum - givenNumbers[i]) &&
			    myHashMap.get(targetSum - givenNumbers[i]) != i)
			{
				output[0] = i;
				output[1] = myHashMap.get(targetSum - givenNumbers[i]);
				break;
			}
		}
		return output;
	}


	
}

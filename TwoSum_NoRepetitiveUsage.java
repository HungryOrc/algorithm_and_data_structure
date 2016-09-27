import java.util.Arrays;

// 要求：返回 indexes
// 元素可能重复，一个元素只能最多用一次
public class TwoSum_NoRepetitiveUsage_ReturnIndexes
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
	public int[] twoSum_ByTwoIndexes(int[] givenNumbers, int targetSum)
    	{
		// put the indexes of the 2 chosen numbers into one array for final output
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

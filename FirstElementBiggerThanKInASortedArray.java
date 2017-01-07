// 运用二分法
public class FirstElementBiggerThanK {
	
	public static int findFirstBiggerThanK (int[] nums, int k)
	{
		Arrays.sort(nums);
		
		int left = 0;
		int right = nums.length;
		
		while (left < right)
		{
			int mid = left + (right-left)/2;
			if (nums[mid] <= k)
				left = mid+1;
			else // nums[mid] > k
				right = mid;
		}
		return nums[right];
  }
}

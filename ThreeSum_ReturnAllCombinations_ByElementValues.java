import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 给一个数组，及一个目标数，要在数组里找3个元素，其和等于目标和
// 要求：返回所有符合要求的组合，里的各个元素的值。所含元素雷同的组合只计为一个组合
// 元素可能重复。一个组合里一个元素只能最多用一次。一个组合里各个元素从小到大排列
public class ThreeSum_ReturnAllCombinations_ByElementValues
{
    // 方法：先排序数组，然后将 3Sum 问题转化为 2Sum 问题。这里用两头index向中间逼近的做法
    // Runtime：O(n^2)
    // 
    // 要使得 a + b + c = 0，即对于任一个元素 A[i],只要判断数组中是否存在 A[j] + A[k] = target - A[i] 即可
    // 这样就转化为 2Sum 问题了。
    // 由于题目要求返回的组合无重复，且一个组合内的元素单调上升，所以最好先对数组进行排序
    //
    public List<List<Integer>> threeSum(int[] givenNumbers)
    {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        Arrays.sort(givenNumbers);

        int n = givenNumbers.length;
        for (int i = 0; i < n - 2; i++)
        {
            int j = i + 1;
            int k = n - 1;

            while (j < k)
            {
                if (givenNumbers[i] + givenNumbers[j] + givenNumbers[k] == 0)
                {
                    List<Integer> tmpArrayList = new ArrayList<Integer>();
                    tmpArrayList.add(givenNumbers[i]);
                    tmpArrayList.add(givenNumbers[j]);
                    tmpArrayList.add(givenNumbers[k]);
                    output.add(tmpArrayList);

                    //skip duplicates of j
                    do
                    {
                        j++;
                    }
                    while (j < k && givenNumbers[j-1] == givenNumbers[j]);
                    //skip duplicates of k
                    do
                    {
                        k--;
                    }
                    while (k > j && givenNumbers[k+1] == givenNumbers[k]);
                }
                else if (givenNumbers[i] + givenNumbers[j] + givenNumbers[k] < 0)
                    j++;
                else
                    k--;
            }
            //skip duplicates of i
            while (i < n - 3 && givenNumbers[i+1] == givenNumbers[i])
                i++;
        }

        return output;
    }
    
    
    // 方法：先排序数组，然后将 3Sum 问题转化为 2Sum 问题。这里用 HashMap 的做法
    // Runtime：O(n^2)
    //
    List<List<Integer>> output = new ArrayList<List<Integer>>();
        Arrays.sort(givenNumbers);
        int n = givenNumbers.length;
        
        HashMap<Integer, Integer> myHashMap = new HashMap<Integer, Integer>();
		for (int i = 2; i < n; i++) // 从2开始是因为前面还有2个数 i 和 j
			myHashMap.put(givenNumbers[i], i);
        
        for (int i = 0; i < n - 2; i++)
        {
        	int targetTwoSum = 0 - givenNumbers[i];

    		for (int j = i + 1; j < n-1; j++)
    		{
    			if (myHashMap.containsKey(targetTwoSum - givenNumbers[j]) &&
    			    myHashMap.get(targetTwoSum - givenNumbers[j]) != j &&
    			    targetTwoSum - givenNumbers[j] >= givenNumbers[j])
    				// 第三个判断条件是为了避免 HashMap 在找到了 -1, 0, 1 以后，
    				// 在找 -1, 1 的第三个搭档的时候，又会去找到 0，从而形成 -1, 1, 0
    			{
    				List<Integer> tmpArrayList = new ArrayList<Integer>();
                    tmpArrayList.add(givenNumbers[i]);
                    tmpArrayList.add(givenNumbers[j]);
                    tmpArrayList.add(targetTwoSum - givenNumbers[j]);
                    output.add(tmpArrayList);
                    
                    // skip duplicates of j
        			while (j < n-2 && givenNumbers[j+1] == givenNumbers[j])
        				j ++;
    			}
    		}
    		//skip duplicates of i
            while (i < n-3 && givenNumbers[i+1] == givenNumbers[i])
                i++;
        }
        return output;
    
    
}

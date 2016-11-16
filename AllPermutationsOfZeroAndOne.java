// 对于一个n位的二进制数，列举出所有可能的0-1排列(Permutation)

// 用 Recursion 做。每一种排列的可能用一个整数数组表示。所有的可能用一个 ArrayList<Integer[]> 表示

import java.util.ArrayList;

public class PermutationOfZeroAndOne {

	public static void main(String[] args) {
		
		int lengthOfArray = 4;
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		Integer[] arrayOfZeroAndOne = new Integer[lengthOfArray];
		
		findPermutations(0, lengthOfArray, arrayOfZeroAndOne, result);
		
		for (Integer[] intArray : result)
		{
			for (int num : intArray)
				System.out.print(num + " ");
			System.out.println();
		}
	}
	
	// this function does not return the result, which is an ArrayList of IntegerArrays,
	// but this function modifies the result in its procedure, 
	// since the result is passed into this function as a reference
	static public void findPermutations(
			int curIndex, int lengthOfArray, Integer[] arrayOfZeroAndOne, ArrayList<Integer[]> result)
	{
		Integer[] newArray1 = new Integer[lengthOfArray];
		Integer[] newArray2 = new Integer[lengthOfArray];
		
		// fill in the digits from index 0 to curIndex-1
		// for this, just copy from the previous array. But make TWO copies!
		for (int i = 0; i < curIndex; i++)
		{
			newArray1[i] = arrayOfZeroAndOne[i];
			newArray2[i] = arrayOfZeroAndOne[i];
		}
		
		// fill in the digit at curIndex
		// for this, fill one with 0 and the other with 1
		newArray1[curIndex] = 0;
		newArray2[curIndex] = 1;
		
		// if the last digit has not been reached, recursively call this function for the next digit
		if (curIndex < lengthOfArray-1)
		{
			findPermutations(curIndex+1, lengthOfArray, newArray1, result);
			findPermutations(curIndex+1, lengthOfArray, newArray2, result);
		}
		else
		{
			result.add(newArray1);
			result.add(newArray2);
		}
	}
}

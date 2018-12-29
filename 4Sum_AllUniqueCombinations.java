

public class Solution {

  public List<List<Integer>> fourSum(int[] array, int target) {
	Arrays.sort(array);  
	  
	List<List<Integer>> result = new ArrayList<>();
	  
        // <pair中2个数的和，和等于前面的key的所有pairs的indexes>
	HashMap<Integer, HashSet<List<Integer>>> pairsSumsAndIndexes = new HashMap<>();
	
	// 所有出现过的pairs的左右元素的值。左右元素都相等的pairs会被去重，因为这里是一个set
	HashSet<List<Integer>> pairsComponents = new HashSet<>();
	
	for (int r = 1; r < array.length; r++) {
	  for (int l = 0; l < r; l++) {
		  
	    int curSum = array[l] + array[r];
	    
	    List<Integer> curValues = new ArrayList<>();
	    curValues.add(array[l]);
	    curValues.add(array[r]);
	    
	    // 如果当前pair的值以前出现过，即当前pair是一个重复的pair，
	    // 那我们就什么都不做，continue到下一个pair
	    if (pairsComponents.contains(curValues)) {
	    	continue;
	    }
	    
     	    // 如果以前出现过（一个或几个）pair能和当前pair的总和加在一起等于target
	    if (pairsIndexesAndSums.containsKey(target - curSum)) { 

	      HashSet<List<Integer>> complementPairs = pairsIndexesAndSums.get(target - curSum);
	       
	      for (List<Integer> pair : complementPairs) {
	         
	    	// 左边的pair的靠右的index，必须小于 右边的pair的靠左的index
		// 如果一路满足到这里，那就算是完成了一个 合格的4数组合
	        if (pair.get(1) < l) { 
	          List<Integer> groupOf4 = new ArrayList<Integer>();   
	          groupOf4.add(array[pair.get(0)]);
	          groupOf4.add(array[pair.get(1)]);
	          groupOf4.add(array[l]);
	          groupOf4.add(array[r]);
	          result.add(groupOf4);
	        }
	      }
	    }
        
            List<Integer> curIndexes = new ArrayList<>();
            curIndexes.add(l);
            curIndexes.add(r);
        
            // 把当前pair的index加到hashmap里去
            if (!pairsIndexesAndSums.containsKey(curSum)) {
              HashSet<List<Integer>> set = new HashSet<>();
              set.add(curIndexes);
    	      pairsIndexesAndSums.put(curSum, set);
            } else {
    	      pairsIndexesAndSums.get(curSum).add(curIndexes);
            }
      
            // 把当前pair的value加到hashset里去
            pairsComponents.add(curValues);
          }
        }
        return result;
  }
}

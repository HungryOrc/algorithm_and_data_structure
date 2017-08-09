/* 条件：
给的元素可能有重复。可以是正负整数或小数。
每个元素最多用一次。

给一个数组
要求返回所有不重复的 4数组合（返回这些数的值，而非这些数在原数组里的index），里面的4个数的和等于给定的 target。
每个4数组合里的数字的排列顺序改变的话，还是算做一个组合。

举例：
int[] input = new int[]{1, 2, 3, 2, 14, 5, 7, 11, 28, 0, 100, 0, -10};
答案：
0 1 2 5 
0 0 3 5 
0 0 1 7 
-10 2 5 11 
-10 0 7 11 
-10 1 3 14       */


/* 思路：首先，基本的思路参考 4 Sum Existence 那一题，那是一种用2个Pair，再加一个HashMap的做法。

就这一题来说，我们需要给出所有可能的组合，且不能重复，这个要求比仅仅判断true/false要高很多。
然后，给的数组里的数字可能是有重复的，所以要去重。一个直观感受是，数组排序以后，会很有利于去重。所以我们要把数组先排序。

接着，我们还是采用 2 pairs 的思想。不过要做点改进。
我们需要把 HashMap 从简单的记录 <sum, pair> 改进到记录 <sum, 所有的和为sum的pairs>，这样才能最终得到所有的组合。
比如 1和3的sum为4, 2和2的sum也为4，那么1和3这个pair，以及2和2这个pair，都要记录在 key即sum=4 的entry的value里面。
这里偷个懒，不再定义class Pair，就直接用一个长度为2的List来代替，第一个元素就是left，第二个元素就是right。所以总的来说就是：
// <pair中2个数的和，和等于前面的key的所有pairs的indexes>
HashMap<Integer, HashSet<List<Integer>>> pairsIndexesAndSums = new HashMap<>();

然后，因为数组里的数字有重复，所以我们光保证 左pair的右index < 右pair的左index 是不够的！
比如 左pair是 5和5，右pair紧接着左pair，没有重叠，也是5和5，就是说这个数组里至少有4个5，这样就造成重复了，而这样的重复用index是检测不出来的。
所以我们还要再搞一个hashset，来存放迄今为止所有遇到过的 pair的值，所谓的值是说它的左右元素的值，而非左右indexes。
注意！HashSet里面可以直接放 List，HashSet对List的equals和hashcode的判断都是ok的！所以再定义一个：
// 所有出现过的pairs的左右元素的值，左右元素都相等的pairs会被去重，因为这里是一个set
HashSet<List<Integer>> pairsComponents = new HashSet<>();

至此，我们就可以开始我们的双层 for loop 了。

对于遇到的每一个pair，如果它的值以前没有出现过（用hashset判断），之前又存在一些pairs与它的和加在一起等于target（用hashmap判断），
那我们把这些pairs都拿出来一个一个看，只要符合这个pair的右index < 当前pair的左index，则凑成一个答案。把这两个pairs加到最终答案里去。

最后，如果当前pair的values以前出现过了（用hashset判断），就是说当前pair是一个重复的pair，那么就什么都不做。
如果当前pair的values以前没有出现过，那就做两件事：
把当前pair的index加到hashmap里去，这里面还涉及map里是否已经有当前pair的sum的key了，不赘述。
把当前pair的value加到hashset里去。   */

public class Solution {

  public List<List<Integer>> fourSum(int[] array, int target) {
	Arrays.sort(array);  
	  
	List<List<Integer>> result = new ArrayList<>();
	  
    // <pair中2个数的和，和等于前面的key的所有pairs的indexes>
	HashMap<Integer, HashSet<List<Integer>>> pairsIndexesAndSums = new HashMap<>();
	
	// 所有出现过的pairs的左右元素的值，左右元素都相等的pairs会被去重，因为这里是一个set
	HashSet<List<Integer>> pairsComponents = new HashSet<>();
	
	for (int r = 1; r < array.length; r++) {
	  for (int l = 0; l < r; l++) {
		  
	    int curSum = array[l] + array[r];
	    
	    List<Integer> curValues = new ArrayList<>();
	    curValues.add(array[l]);
	    curValues.add(array[r]);
	    
	    // 如果当前pair的值以前没有出现过，即当前pair不是一个重复的pair
	    if (!pairsComponents.contains(curValues) && 
	        // 而且，以前出现过至少一个pair能和当前pair的总和加在一起等于target
	    	pairsIndexesAndSums.containsKey(target - curSum)) { 
	    	
	      HashSet<List<Integer>> complementPairs = pairsIndexesAndSums.get(target - curSum);
	       
	      for (List<Integer> pair : complementPairs) {
	         
	    	// 左边的pair的靠右的index，必须小于 右边的pair的靠左的index
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
	    
	    // 如果当前pair的值以前没有出现过，即当前pair不是一个重复的pair
        if (!pairsComponents.contains(curValues)) {
        
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
    }
	
    return result;
  }
}
